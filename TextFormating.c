#include "stdio.h"
#include "conio.h"
#include "ctype.h"
#include "string.h"

#define MAX_WORD_LENS 20

int readWord(char *word);
void clearLine();
void writeLineLeft(char *line);
void writeLineRight(char *line, int lineLen);
void writeLineLR(char *line, int lineLen, int numWords);
void textFormat();

int MAX_LINE_LENS;
FILE *f1, *f2;

int main() {
    char text[1000];
    char word[MAX_WORD_LENS + 1];
    int wordLen, ch, i, ch1;
    char *fileName;
    printf("So ki tu toi da tren 1 dong: ");
    scanf("%d", &MAX_LINE_LENS);
    char line [MAX_LINE_LENS + 1];
    int lineLen = 0;
    int numWords = 0;
    printf("Input:\n1. Tu ban phim\n2. Tu File\n");
    scanf("%d", &ch1);
    switch (ch1) {
    case 1:
        f1 = fopen("file_ban_dau.txt", "wt");
        printf("Nhap doan van ban:\n----------------\n");
        fflush(stdin);
        gets(text);
        for (i = 0 ; i < strlen ( text ) ; i++ ) {
            if ((text[i] == '\\') && (text[i+1] == 'n'))
                putc((int) '\n', f1);
            else
                putc((int) text[i], f1);
        }
        fclose (f1);

        f1 = fopen ("file_ban_dau.txt", "rt");
        f2 = fopen ("file_da_can_le.txt", "wt");
        textFormat();
        break;

    case 2:
        printf("\nFlie nguon: ");
        fflush(stdin);
        gets(fileName);
        f1 = fopen(fileName, "rt");
        if ((f1 = fopen(fileName, "r")) == NULL) {
            fprintf(stderr, "File not found\n");
            return 0;
        }
        f2 = fopen ("file_da_can_le.txt", "wt");
        textFormat();
        break;
    }
    fclose(f1);
    fclose(f2);
    getch();
    return 0;
}


int readWord(char *word) {
    int ch, pos = 0;
    ch = getc(f1);
    while (isspace(ch))
        ch = getc(f1);
    while (!isspace(ch) && (ch != EOF)) {
        if (pos < MAX_WORD_LENS) {
            word[pos] = (char) ch;
            pos++;
        }
        ch = getc(f1);
    }
    word[pos] = '\0';
    return pos;
}

void clearLine(char *line, int *lineLen, int *numWords) {
    line[0] = '\0';
    *lineLen = 0;
    *numWords = 0;
}

void addWord(char *word, char *line, int *lineLen) {
    if (*lineLen > 0) {
        line[*lineLen] = ' ';
        line[*lineLen + 1] = '\0';
        (*lineLen)++;
    }
    strcat(line, word);
    (*lineLen) += strlen(word);
}

void writeLineLR(char *line, int lineLen, int numWords) {
    int extraSpaces, spacesToInsert, i, j;
    extraSpaces = MAX_LINE_LENS - lineLen;
    for (i = 0; i < lineLen; i++) {
        if (line[i] != ' ') {
            printf ("%c", line[i]) ;
            putc((int) line[i], f2);
        } else {
            spacesToInsert = extraSpaces / (numWords - 1);
            for (j = 1; j <= (spacesToInsert + 1); j++) {
                printf(" ");
                putc((int) ' ', f2);
            }
            extraSpaces -= spacesToInsert;
            numWords--;
        }
    }
    printf ("\n");
    putc ((int) '\n' ,f2);
}

void writeLineLeft (char *line) {
    int i;
    puts (line);
    printf ("\n");
    for (i = 0; i < strlen (line); i++)
        putc ((int) line[i], f2) ;
    putc ((int) '\n' , f2) ;
}

void writeLineRight (char *line ,int linelen) {
    int extraSpaces, i;
    extraSpaces = MAX_LINE_LENS - linelen ;
    for (i = 1; i <= extraSpaces; i++) {
        printf (" ") ;
        putc ((int) ' ' ,f2);
    }
    puts(line);
    printf ("\n") ;
    for (i = 0; i < strlen(line); i++)
        putc ((int) line[i], f2);
    putc ((int) '\n', f2);
}

void textFormat() {
    char text[1000];
    char word[MAX_WORD_LENS + 1];
    int wordLen, ch, i, ch1;
    char line [MAX_LINE_LENS + 1];
    int lineLen = 0;
    int numWords = 0;
    printf("\n1. Can le trai\n2. Can le phai\n3. Can le trai phai");
    scanf("%d", &ch);
    printf ("\nVan ban sau khi duoc dinh dang: \n\n");
    clearLine(line, &lineLen, &numWords);
    if (ch == 1) {
        for( ; ; ) {
            wordLen = readWord ( word ) ;
            if((wordLen == 0) && (lineLen > 0)) {
                puts(line);
                for(i = 0; i < strlen(line); i++)
                    putc((int) line[i], f2);
                break;
            }
            if ((wordLen + 1 + lineLen ) > MAX_LINE_LENS) {
                writeLineLeft(line);
                clearLine(line ,&lineLen ,&numWords);
            }
            addWord (word , line, &lineLen);
            numWords++;
        }
    }
    if (ch == 2) {
        clearLine (line, &lineLen, &numWords);
        for( ; ; ) {
            wordLen = readWord(word);
            if((wordLen == 0) && (lineLen > 0)) {
                writeLineRight(line, lineLen);
                break;
            }
            if ((wordLen + 1 + lineLen) > MAX_LINE_LENS) {
                writeLineRight(line, lineLen);
                clearLine (line, &lineLen, &numWords);
            }
            addWord (word, line, &lineLen);
            numWords++;
        }
    }
    if (ch == 3) {
        for( ; ; ) {
            wordLen = readWord (word);
            if((wordLen == 0) && (lineLen > 0)) {
                writeLineLeft(line) ;
                break;
            }
            if ((wordLen + 1 + lineLen) > MAX_LINE_LENS) {
                writeLineLR(line, lineLen, numWords);
                clearLine(line, &lineLen, &numWords);
            }
            addWord(word, line, &lineLen);
            numWords++;
        }
    }
}
