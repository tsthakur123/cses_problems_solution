package CodeForces;
import java.util.*;
import java.io.*;

public class IncreasingSequencewithFixedOR {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            long n = Long.parseLong(br.readLine().trim());
            List<Long> seq = new ArrayList<>();
            long x = n;
            while (x > 0) {
                seq.add(x);
                x = (x - 1) & n; 
            }
            Collections.reverse(seq);
            sb.append(seq.size()).append("\n");
            for (long val : seq) sb.append(val).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
