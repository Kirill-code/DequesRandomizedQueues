import edu.princeton.cs.algs4.StdIn;


public class Permutation {
    public static void main(String[] args) {


        int i = Integer.parseInt(args[0]);
        RandomizedQueue<Object> rQ = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) rQ.enqueue(StdIn.readString());
        for (int j = 0; j < i; j++) {
            System.out.println(rQ.dequeue());
        }


    }
}
