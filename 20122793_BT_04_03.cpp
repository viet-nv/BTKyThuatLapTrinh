#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
#include<string.h>
#define MAX 100

char **nhap_sv(char**,int);
char **sap_xep(char**,int);

int main() {
    printf("Nhap vao so sinh vien cua lop: ");
    int n;
    scanf("%d",&n);
    char **ho_ten=(char**)calloc(n,sizeof(char*));
    ho_ten=nhap_sv(ho_ten,n);
    ho_ten=sap_xep(ho_ten,n);
    printf("\n\a\a\aDanh sach sau khi da sap xep:\n");
    printf("\n------------------------------------------\n");
    printf("|STT |            Ho va ten              |\n");
    printf("------------------------------------------\n");
    for(int i=0; i<n; i++) {
        printf("| %-3d|     %-30s|\n",i+1,*(ho_ten+i));
        printf("------------------------------------------\n");
    }
    getch();
    return 0;
}

//Chuan hoa: loai bo cac dau cach 2 dau va dau cach du thua o giua.
char *chuan_hoa(char *name) {
    int i=0,j=0;
    char *temp=(char*)calloc(MAX,sizeof(char));
    while(*(name+i)!='\0') {
        if(*(name+i)!=32) {
            *(temp+j)=*(name+i);
            i++;
            j++;
        }//end if1
        else {
            if(i==0) {
                while(*(name+i)==32) {
                    i++;
                }//end while
            }//end if2
            else {
                if(*(name+i+1)==32||*(name+i+1)=='\0') i++;
                else {
                    *(temp+j)=*(name+i);
                    i++;
                    j++;
                }
            }//end else2
        }//end else1
    }//end while
    return temp;
}

char **nhap_sv(char **ho_ten,int n) {
    int i,j;
    for(int i=0; i<n; i++) {
        *(ho_ten+i)=(char*)calloc(50,sizeof(char));
        printf("\nSinh vien %d: ",i+1);
        fflush(stdin);
        gets(*(ho_ten+i));
        *(ho_ten+i)=chuan_hoa(*(ho_ten+i));
    }
    return ho_ten;
}
//So sanh 2 ten
int compare(char *name1,char *name2) {
    int i=strlen(name1)-1,j=strlen(name2)-1,k;
    while(*(name1+i)!=32) {
        i--;
    }
    while(*(name2+j)!=32) {
        j--;
    }
    char *temp1=(char*)calloc(MAX,sizeof(char));
    char *temp2=(char*)calloc(MAX,sizeof(char));
    for(k=0; k<strlen(name1)-i-1; k++) {
        *(temp1+k)=*(name1+i+1+k);
    }
    for(k=0; k<strlen(name2)-j-1; k++) {
        *(temp2+k)=*(name2+j+1+k);
    }
    if(stricmp(temp1,temp2)>0) return 1;
    if(stricmp(temp1,temp2)==0) {
        strncpy(temp1,name1,i);
        strncpy(temp2,name2,j);
        if(stricmp(temp1,temp2)>0) return 1;
        else return 0;
    } else return 0;
}
//Doi cho
void swapName(char *name1,char *name2) {
    char *temp=(char*)calloc(MAX,sizeof(char));
    strcpy(temp,name1);
    strcpy(name1,name2);
    strcpy(name2,temp);
}
//Sap xep ten theo thu tu tu dien
char **sap_xep(char **ho_ten,int n) {
    int i,j;
    for(i=n-1; i>=0; i--) {
        for(j=1; j<=i; j++) {
            if(compare(*(ho_ten+j-1),*(ho_ten+j))) {
                swapName(*(ho_ten+j-1),*(ho_ten+j));
            }
        }
    }
    return ho_ten;
}
