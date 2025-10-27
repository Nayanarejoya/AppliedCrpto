#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define SIZE 2
int modInverse(int a, int m) {
    a = a % m;
    for (int x = 1; x < m; x++)
        if ((a * x) % m == 1)
            return x;
    return -1;}
int computeInverseKey(int key[SIZE][SIZE], int inverseKey[SIZE][SIZE]) {
    int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
    det = (det % 26 + 26) % 26;
    int detInv = modInverse(det, 26);
    if (detInv == -1) {
        printf("Key matrix is not invertible modulo 26.\n");
        return 0;}
    inverseKey[0][0] = key[1][1];
    inverseKey[0][1] = -key[0][1];
    inverseKey[1][0] = -key[1][0];
    inverseKey[1][1] = key[0][0];
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            inverseKey[i][j] = (inverseKey[i][j] * detInv) % 26;
            if (inverseKey[i][j] < 0)
                inverseKey[i][j] += 26;}}
    printf("Inverse Key Matrix (mod 26):\n");
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++)
            printf("%3d", inverseKey[i][j]);
        printf("\n");}
    return 1;}
void encrypt(char plain[], int key[SIZE][SIZE], char cipher[]) {
    int len = strlen(plain);
    if (len % SIZE != 0) {
        plain[len++] = 'X';
        plain[len] = '\0';
    }
    printf("\nEncrypted Text: ");
    int idx = 0;
    for (int i = 0; i < len; i += SIZE) {
        int result[SIZE] = {0};
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                result[j] += key[j][k] * (toupper(plain[i + k]) - 'A');
            }
            result[j] %= 26;}
        for (int j = 0; j < SIZE; j++) {
            cipher[idx++] = result[j] + 'A';
            printf("%c", cipher[idx - 1]);
        }}
    cipher[idx] = '\0';
    printf("\n");
}
void decrypt(char cipher[], int inverseKey[SIZE][SIZE]) {
    int len = strlen(cipher);
    printf("Decrypted Text: ");
    for (int i = 0; i < len; i += SIZE) {
        int result[SIZE] = {0};
        for (int j = 0; j < SIZE; j++) {
            for (int k = 0; k < SIZE; k++) {
                result[j] += inverseKey[j][k] * (toupper(cipher[i + k]) -'A');}
            result[j] %= 26;}
        for (int j = 0; j < SIZE; j++)
            printf("%c", result[j] + 'A');}
    printf("\n");}
int main() {
    char plain[100];
    char cipher[100];
    int key[SIZE][SIZE], inverseKey[SIZE][SIZE];
    printf("Enter the plaintext (letters only): ");
    fgets(plain, sizeof(plain), stdin);
    plain[strcspn(plain, "\n")] = '\0';
    int idx = 0;
    for (int i = 0; plain[i]; i++) {
        if (isalpha(plain[i]))
            plain[idx++] = toupper(plain[i]);
    }
    plain[idx] = '\0';
    printf("Enter the 2x2 key matrix (4 integers):\n");
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            scanf("%d", &key[i][j]);
        }}
    encrypt(plain, key, cipher);
    if (computeInverseKey(key, inverseKey)) {
        decrypt(cipher, inverseKey);
    }
    return 0;}

