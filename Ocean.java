/*
This is my solution to the Kattis problem Ocean's Anti-11, found here:
https://open.kattis.com/problems/anti11

The input consists of a number of integers n <= 10,000.
The required output for each n is the nth Fibonacci number mod (10^9 + 7).

The O(logn) algorithm implemented below adds powers of the Fibonacci transfer matrix
to find the nth Fibonacci number.

Another solution could be to precompute a table of 10,000 Fibonacci numbers % (10^9 + 7)
in about 10,000 steps. This would avoid overhead from matrix multiplication, and would be fine for 
the current bound on n, but would not be efficient if the upper bound on n were very large.

The program assumes a 64-bit long.
*/


import java.util.*;

public class Ocean {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int j = 0; j < n; j++) {//for each testcase 
            char[] bitstring = Integer.toBinaryString(in.nextInt()).toCharArray();//write as array 
            long[][] Apow = {
              {1, 1},
              {1, 0}
            };
            long[][] Afin = {
              {1, 0},
              {0, 1}
            };
            for (int i = bitstring.length - 1; i >= 0; i--) {
                if (bitstring[i] == '1') {
                    Afin = multiply(Afin, Apow);
                }               
                Apow = multiply(Apow, Apow);
            }
            System.out.println((Afin[0][0] + Afin[0][1])%(1000000007));
        }
    }
    
	//Returns the product of two 2x2 matrices.
    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] ret = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ret[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
                ret[i][j] %= 1000000007;
            }
        }
        return ret;
    }   
}
