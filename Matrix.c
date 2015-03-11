#include "stdio.h"
#include "conio.h"
#include "math.h"
#define max 100

void nhapMaTran(float a[][max], int m, int n);
void xuatMaTran(float a[][max], int m, int n);
void congMaTran(float a[][max], float b[][max], float c[][max], int m, int n);
void nhanMaTran(float a[][max], float b[][max], float c[][max], int m, int n, int p);
void invMatrix(float a[][max], float b[][max], int n);

int main() {
    int ch, m, n, p;
    float a[max][max], b[max][max], c[max][max];
    int i;
tt:
    system("cls");
    printf("\n-------------MENU-------------");
    printf("\n1. Cong 2 ma tran");
    printf("\n2. Nhan 2 ma tran");
    printf("\n3. Tim ma tran nghich dao");
    printf("\n4. Thoat.\n");
    scanf("%d",&ch);
    switch(ch) {
    case 1:
tt1:
        system("cls");
        printf("\nNhap cap cua ma tran: ");
        printf("\nSo dong: ");
        scanf("%d",&m);
        printf("\nSo cot: ");
        scanf("%d",&n);
        printf("\nNhap ma tran A cap %dx%d", m, n);
        nhapMaTran(a,m,n);
        printf("\nNhap ma tran B cap %dx%d", m, n);
        nhapMaTran(b,m,n);
        printf("\nMa tran A vua nhap: ");
        xuatMaTran(a,m,n);
        printf("\nMa tran B vua nhap: ");
        xuatMaTran(b,m,n);
        congMaTran(a,b,c,m,n);
        printf("\n\nMa tran C=A+B");
        xuatMaTran(c,m,n);
        printf("\n\n1. Tiep tuc");
        printf("\n2. Ve Menu");
        printf("\n3. Ket thuc.");
        scanf("%d",&i);
        if (i==1) goto tt1;
        else if (i==2) goto tt;
        else break;

    case 2:
tt2:
        system("cls");
        printf("\nNhap 2 ma tran can nhan: ");
        printf("\nNhap so hang cua ma tran A: ");
        scanf("%d",&m);
        printf("\nNhap so cot cua ma tran A (so hang cua ma tran B):");
        scanf("%d",&n);
        printf("\nNhap so cot cua ma tran B: ");
        scanf("%d",&p);
        printf("\nNhap ma tran A cap %dx%d: ",m,n);
        nhapMaTran(a,m,n);
        printf("\nNhap ma tran B cap %dx%d: ",n,p);
        nhapMaTran(b,n,p);
        printf("\nMa tran A vua nhap: ");
        xuatMaTran(a,m,n);
        printf("\nMa tran B vua nhap: ");
        xuatMaTran(b,n,p);
        nhanMaTran(a,b,c,m,n,p);
        printf("\nMa tran C=AxB ");
        xuatMaTran(c,m,p);
        printf("\n\n1. Tiep tuc");
        printf("\n2. Ve Menu");
        printf("\n3. Ket thuc.");
        scanf("%d",&i);
        if (i==1) goto tt2;
        else if (i==2) goto tt;
        else break;
    case 3:
tt3:
        system("cls");
        printf("\nNhap cap cua ma tran: n = ");
        scanf("%d",&n);
        printf("\nNhap ma tran vuong cap %d",n);
        nhapMaTran(a,n,n);
        printf("\nMa tran vua nhap: ");
        xuatMaTran(a,n,n);
        invMatrix(a,b,n);
        printf("\n\n1. Tiep tuc");
        printf("\n2. Ve Menu");
        printf("\n3. Ket thuc.");
        scanf("%d",&i);
        if (i==1) goto tt3;
        else if (i==2) goto tt;
        else break;

    case 4:
        break;
    }
    getch();
    return 0;
}

void nhapMaTran(float a[][max], int m, int n) {
    int i, j;
    for (i=0; i<m; i++) {
        for (j=0; j<n; j++) {
            printf("\nA[%d][%d] = ", i+1, j+1);
            scanf("%f",&a[i][j]);
        }
    }
}

void xuatMaTran(float a[][max], int m, int n) {
    int i, j;
    for (i=0; i<m; i++) {
        printf("\n");
        for (j=0; j<n; j++) {
            printf("%8.2f  ",a[i][j]);
        }
    }
}

void congMaTran(float a[][max], float b[][max], float c[][max], int m, int n) {
    int i, j;
    for (i=0; i<m; i++) {
        for (j=0; j<n; j++) {
            c[i][j] = a[i][j] + b[i][j];
        }
    }
}

void nhanMaTran(float a[][max], float b[][max], float c[][max], int m, int n, int p) {
    int i, j, k;
    for (i=0; i<m; i++) {
        for (k=0; k<p; k++) {
            c[i][k]=0;
            for (j=0; j<n; j++) c[i][k] = c[i][k] + a[i][j]*a[j][k];
        }
    }
}

//tinh (-1)^n
float sign(int n) {
    if (n%2==0) return 1.0;
    return -1.0;
}

//tim ma tran con
void newMatrix(float a[][max], float b[][max], int x, int y, int n) {
    int i, j;
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            b[i][j] = a[i][j];
    for (i = x; i < n; i++)
        for (j = 0; j < n; j++)
            b[i][j] = b[i+1][j];
    for (j = y; j < n; j++)
        for (i = 0; i < n; i++)
            b[i][j] = b[i][j+1];
}

//tinh dinh thức
float detMatrix(float a[][max], int n) {
    int i, j, det = 0;
    float b[max][max];
    if (n == 1)
        return a[0][0];
    else
        for (j=0; j<n; j++) {
            newMatrix(a, b, 0, j, n);
            det+=a[0][j]*sign(0+j) * detMatrix(b, n - 1);
        }
    return det;
}

//Tim ma tran nghich dao
void invMatrix(float a[][max], float b[][max], int n) {
    int k, i, j;
    float det, temp;
    det = detMatrix(a,n);
    if (det != 0) {
        for (i = 0; i < n; i++) // Tạo ra b là ma trận đơn vị
            for (j = 0; j < n; j++)
                if (i==j) b[i][j] = 1;
                else b[i][j] = 0;

        for (i = 0; i < n; i++) { // Xử lý từ hàng đầu đến hàng cuối
            if (a[i][i] == 0) { // Nếu gặp phần tử trên đường chéo chính bằng 0 thì đổi hàng
                k = i+1;
                while (k < n && a[k][i] == 0) k++;
                for (j = 0; j < n; j++) { // Đổi hàng đó của a đó, cả với ma trận b nữa
                    temp = a[i][j];
                    a[i][j] = a[k][j];
                    a[k][j] = temp;
                    temp = b[i][j];
                    b[i][j] = b[k][j];
                    b[k][j] = temp;
                }
            }

            temp = a[i][i];
            for (j = i; j < n; j++) a[i][j] /= temp;
            for (j = 0; j < n; j++) b[i][j] /= temp;

            for (j = i+1; j < n; j++) {
                temp = -a[j][i];
                for (k = i; k < n; k++) a[j][k] += temp*a[i][k];
                for (k = 0; k < n; k++) b[j][k] += temp*b[i][k];
            }
        }// Kết thúc quá trình Gauss

        for (i = n-1; i > 0; i--) // Bắt đầu quá trình Jordan
            for (j = i-1; j >=0; j--) {
                temp = -a[j][i];
                for (k = n-1; k >= i; k--)  a[j][k] += temp*a[i][k];
                for (k = 0; k < n; k++) b[j][k] += temp*b[i][k];
            }
        printf("\nMa tran nghich dao cua A: ");
        xuatMaTran(b,n,n);
    } else printf("\nKhong co ma tran nghich dao\n");
}
