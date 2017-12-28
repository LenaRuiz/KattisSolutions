/*
This is my solution the the Kattis problem War on Weather. Given a set of sattelites
and a set of points on the Earth, the problem asks how many points are visible
from at least one sattelite. My program uses the fact that a point will be visible
from a sattelite iff the angle SCP is <= 90 degrees, where S is the position of the 
sattelite, C is the centre of the Earth and P is the point on the earth's surface.
*/

import java.util.*;
import java.math.*;

public class War {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (in.hasNext()) {
            int k = in.nextInt();//number of sattelites
            int m = in.nextInt();//number of tropical depressions
            if (k == 0 && m == 0) System.exit(0);
            in.nextLine();
            double[][] sattelites = new double[k][3];
            double[][] depressions = new double[m][3];
            String[] tmp;
            for (int i = 0; i < k; i++) {   
                tmp = in.nextLine().split(" ");
                sattelites[i][0] = Double.parseDouble(tmp[0]);
                sattelites[i][1] = Double.parseDouble(tmp[1]);
                sattelites[i][2] = Double.parseDouble(tmp[2]);
            }
            for (int i = 0; i < m; i++) {
                tmp = in.nextLine().split(" ");
                depressions[i][0] = Double.parseDouble(tmp[0]);
                depressions[i][1] = Double.parseDouble(tmp[1]);
                depressions[i][2] = Double.parseDouble(tmp[2]);
            }
            boolean[] hit = new boolean[m];//mark depresions as true when we find a sattelite that hits them
            int numHit = 0;
            for (int sattelite = 0; sattelite < k; sattelite++) {
                
                for (int depression = 0; depression < m; depression++) {
                    if (!hit[depression]) {
                        double[] v1 = {0 - depressions[depression][0],0-depressions[depression][1],0-depressions[depression][2]};//vector from point to depression
                        double[] v2 = {sattelites[sattelite][0]-depressions[depression][0],sattelites[sattelite][1]-depressions[depression][1],sattelites[sattelite][2]-depressions[depression][2]};//vector from sattelite to depression
                        double dot = v1[0]*v2[0] + v1[1]*v2[1] + v1[2]*v2[2];
                        if (dot <= 0) {//check if the is obtuse.
                            hit[depression] = true;
                            numHit++;
                        }
                    }
                }
            }
            System.out.println(numHit);
        }
    }   
} 
