import java.io.*;
import java.util.*;

/**
 * CSES: K Smallest Subset Sums
 * Time:  O(n log n + k log k)
 * Memory: O(n + k)
 */
public class P3108 {
    static class Node {
        long sum; // current subset sum over b[0..idx]
        int idx;  // last index used
        Node(long s, int i) { sum = s; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = fr.nextInt();
        int k = fr.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fr.nextLong();

        // 1) Base = sum of negatives; 2) b = abs(a[i]) sorted ascending (non-negative)
        long base = 0;
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) base += a[i];
            b[i] = Math.abs(a[i]);
        }
        Arrays.sort(b);

        // We need k smallest values of form base + (subset sum over b)
        ArrayList<Long> ans = new ArrayList<>(k);
        ans.add(base);                   // empty subset over b

        if (k > 1 && n > 0) {
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(x -> x.sum));
            pq.add(new Node(b[0], 0));  // start with taking b[0]

            while (ans.size() < k && !pq.isEmpty()) {
                Node cur = pq.poll();
                ans.add(base + cur.sum);

                int i = cur.idx;
                if (i + 1 < n) {
                    // Option A: keep current selection and also take b[i+1]
                    pq.add(new Node(cur.sum + b[i + 1], i + 1));
                    // Option B: replace b[i] with b[i+1] (move the "last taken" forward)
                    pq.add(new Node(cur.sum - b[i] + b[i + 1], i + 1));
                }
            }
        }

        // Print first k results
        for (int i = 0; i < k; i++) {
            if (i > 0) out.print(' ');
            out.print(ans.get(i));
        }
        out.println();
        out.flush();
    }

    // Fast input
    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }
}
