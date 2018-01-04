import java.util.*;

/*
This is my solution to the Kattis problem Bank Queue.

One can make two observations:
    1. However many customers have been served so far, it is best to select a customer with 
        the largest deposit size from the remaining customers.
    
    2. To minimize interference with other patrons, it is best for each patron to be served as late as possible.
        
These observations lead to the greedy approach below.

Algorithm: 
    Sort customers in descending order by time deposit size. 
    For customers with the same deposit size, sort in descending order of patience.
    while time remains:
        remove the next avaliable person
        if any times before their last available time are free:
            serve them at the latest such time
            decrement remaining time            
*/
public class BankQueue {

    public static void main(String[] args) {
            maxProfit();
    }

    public static void maxProfit() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int T = in.nextInt();
        long totalCash = 0;
        long soFar = 0;
        //The priority queue makes this algorithn O(nlognlogn). A sorted array would have worked 
        //as well and run in O(nlogn) time, but the priority queue is more convenient for this
        //program, and the extra factor of log(n) is not significant given the input size.
        PriorityQueue<Person> pq = new PriorityQueue<Person>(N, Collections.reverseOrder());
        
        for (int i = 0; i < N; i++) {
            pq.add(new Person(in.nextInt(), in.nextInt()));
        }
        
        boolean[] select = new boolean[T];
        Person nextPerson;
        while (soFar < N && !pq.isEmpty()) {
            nextPerson = (Person)pq.poll();
            int start = nextPerson.time;
            
            while (start >= 0 && select[start]) {
                start--;
            }
            
            if (start != -1) {
                soFar++;
                select[start] = true;
                totalCash += nextPerson.cash;
            }
        }
        System.out.println(totalCash);  
    }
}

class Person implements Comparable<Person> {
    int cash;
    int time;
    
    public Person(int c, int t) {
        this.cash = c;
        this.time = t;
    }
    
    //@override
    public int compareTo(Person p) {
        int pc = p.cash;
        int pt = p.time;
        
        if (cash < pc) return -1;
        if (cash > pc) return 1;
        if (time < pt) return -1;
        if (time > pt) return 1;
        return 0;
    }
}


