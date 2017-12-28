/*
This is my solution to the Kattis problem That's One Hanoi-ed Teacher.
The problem statement asks for a ranking algorithm for states of a 
Hanoi puzzle. This can be done recursively (in terms of the number of disks)
with a reordering of the stacks at each step. The base case is an empty
puzzle.

*/


import java.util.Scanner;
import java.util.LinkedList;

public class Hanoi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> A = new LinkedList<Integer>();//first stack
        LinkedList<Integer> B = new LinkedList<Integer>();//second stack
        LinkedList<Integer> C = new LinkedList<Integer>();//third stack, let first() be the bottom and last() be the top of the stack.
        int n = in.nextInt();
        for (int i = 0; i < n; i++) A.addLast(new Integer(in.nextInt()));
        n = in.nextInt();
        for (int i = 0; i < n; i++) B.addLast(new Integer(in.nextInt()));
        n = in.nextInt();
        for (int i = 0; i < n; i++) C.addLast(new Integer(in.nextInt()));
        n = A.size() + B.size() + C.size();//number of pegs
        long num = solve(A, B, C, n);
        System.out.println(num == -1 ? "No" : (long)Math.pow(2, n) - 1 - num);
    }
    
    public static long solve(LinkedList<Integer> A, LinkedList<Integer> B, LinkedList<Integer> C, int n/*number of disks*/) {
        if (n == 0) return 0;
        //in the second 2^n-1 moves, the nth disk is at the bottom of C.
        //in the first 2^n-1 moves, the nth disk is at the bottom of A.
        //if the nth disk is in neither of those two locations, then the thing is invalid.
        if (A.size() != 0 && (int)A.getFirst() == n) {
            A.removeFirst();
            //rearrange pointers to swap 2nd and 3rd stacks
            return solve(A, C, B, n - 1);
        } else {
            if (C.size() != 0 && (int)C.getFirst() == n) {
                C.removeFirst();
                //rearrange pointers to swap 1st and 2nd stacks
                long ret = solve(B, A, C, n - 1);
                return (ret == -1 ? -1 : (long)Math.pow(2, n - 1) + ret);
            } else {
                return -1;
            }
        }
    }
} 
