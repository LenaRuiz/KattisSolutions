/*
This is my solution to the Kattis problem Relatives, which asks for the
Euler phi function of a number. The program makes use of the fact that 
the function is multiplicative in relatively prime factors by first 
reducing the number to prime powers.
*/

import java.util.*;
import java.math.*;

public class Relatives {
    public static void main(String[] args) {
        //Store prime numbers before solving test cases.
        int maxrt = 31626;
        int maxrtrt = 180;
        boolean[] comp = new boolean[maxrt];//sieve of Eritosthenes
        comp[1] = true;
        for (int i = 2; i <= maxrtrt; i++) {
            if (!comp[i]) {
                for (int j = i+i; j < maxrt; j += i) {
                    comp[j] = true;
                }
            }
        }
        
        //Solve all test cases from stdin
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;//input is termintated by 0
            System.out.println(eulerPhi(n, comp));
        }
    }
    
    public static int eulerPhi(int n, boolean[] comp) {
        if (n == 1) return 0;//end of input
        int ret = 1;
        int bound = (int)Math.floor(Math.sqrt(n));//to factor n, we only need to check factors up to here
        for (int i = 2; i <= bound; i++) {
            if (!comp[i]) {
                int count = 0;
                while ((n % i) == 0) {
                    n /= i;
                    count++;
                }
                if (count != 0) {//Euler phi of a prime power 
                    if (count == 1) ret *= (i - 1);
                    else ret *= (int)(Math.pow(i, count) - Math.pow(i, count - 1));
                }
            }
        }
        if (n != 1) ret *= (n - 1);//n may have had one factor greater than sqrt(n)
        
        return ret;
    }
} 
