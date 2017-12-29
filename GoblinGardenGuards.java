/*
This is my solution to the Kattis problem Goblin Garden Guards.
It is optimized by iterating over small squares containing sprinklers and
checking the number of goblins at a point before checking the point's distance from the sprinkler.
*/

import java.util.Scanner;

class GoblinGardenGuards {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        int[][] numGoblins = new int[10001][10001];//lawn given in input
        
        for (int i = 0; i < g; i++) {
            numGoblins[in.nextInt()][in.nextInt()]++;//add each goblin to to its location on the lawn
        }
        
        int m = in.nextInt();
        
        for (int i = 0 ; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            int x2 = x*x;
            int y2 = y*y;
            int xy = x*y;
            int r2 = r*r;
            
            //Save time by iterating over small squares containing circles we want to examine.
            //Since most points in the square will be within the circle, this saves a lot of
            //unnecessary calculations of distance from the centre of the circle.
            for (int j = Math.max(x - r, 0); j <= Math.min(x + r, 10000); j++) {
                for (int k = Math.max(y - r, 0); k <= Math.min(y + r, 10000); k++) {
                    //Save time by checking if there actually are goblins at the point in question
                    //before bothering to check distance from a sprinkler.
                    if (numGoblins[j][k] > 0 && x2 + j*j- 2*x*j + y2 + k*k - 2*y*k <= r2) {
                        g -= numGoblins[j][k];
                        numGoblins[j][k] = 0;
                    }
                }    
            }
        }
        System.out.println(g);
    }
}
