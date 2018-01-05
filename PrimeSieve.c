/*
This is my solution to the Kattis problem Prime Sieve https://open.kattis.com/problems/primesieve .
The problem statement:

    Input:

    The first line of input consists of two integers n, q, where 1 <= n <= 10^8 and 1<= q <= 20,000. 
    Then follow q lines, each containing an integer x satisfying 1<= x <= n.

    Output:

    On the first line of output, write one line giving the number of prime numbers less than or equal to n. 
    Then for each query x, output 1 if x is a prime and ouput 0 if x is composite. 

This can be solved by storing a sieve of Eritosthenes with n entries, counting all the primes up to n, 
and then querying individual values. 
The memory limit is 64MB, too small to store the entire sieve as a boolean array, so the program uses a 
char array with n/8 entries and uses it to store a sieve with one bit per entry.

*/

#include<stdio.h>

//Creates a bit-packed Sieve of Eritosthenes with n entries.
int eritosthenes(char b[], int n) {
    for (int i = 2; i < n; i++) {
        if (!((b[i / 8] >> (i % 8) & 0x01) == 1)) {//if bit not set (number is prime)
            for (int j = i + i; j < n; j += i) {
                b[j >> 3] |= (0x01 << (j & 0x7));//set bit (mark its multtples as composite)
            }
        }
    }
    b[1 / 8] |= (0x01 << (1 % 8));
    int ret = 0;
    for (int i = 2; i < n; i++) if (!((b[i >> 3] >> (i & 0x07) & 0x01) == 1)) ret++;
    return ret;
}

int main() {       
    int n;
    scanf("%d", &n);
    int m;
    scanf("%d", &m);
    char b[(n + 1) / 8 + 1];
    int len = (n + 1) / 8 + 1;
    for (int i = 0; i < len; i++) b[i] = 0;
    int count = eritosthenes(b, n + 1);
    printf("%d\n", count);
    int i = 0;
    int M[m];
    for (i = 0; i < m; i++) {
        scanf("%d", &M[i]);
    }
    for (int j = 0; j < m; j++) {
        i = M[j];
        printf("%d\n", ((b[i / 8] >> (i % 8) & 0x01) == 1) ? 0 : 1);
    }        
} 
