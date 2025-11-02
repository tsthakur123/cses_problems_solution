import java.util.*;
import java.io.*;

public class FriendsAndTheRestaurant_1729D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] xStr = br.readLine().split(" ");
            String[] yStr = br.readLine().split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(xStr[i]);
                int y = Integer.parseInt(yStr[i]);
                arr[i] = y - x;
            }
            Arrays.sort(arr);
            boolean[] used = new boolean[n];
            int res = 0;

            for (int i = 0; i < n; i++) {
                if (used[i]) continue;
                int idx = helper(arr, -arr[i], i + 1, n - 1);
                while (idx < n && used[idx]) {
                    idx++;
                }
                if (idx < n) {
                    used[i] = true;
                    used[idx] = true;
                    res++;
                }
            }
            System.out.println(res);
        }
    }
    static int helper(int[] arr, int t, int st, int e) {
        int ans = arr.length;
        while (st <= e) {
            int mid = st + (e - st) / 2;
            if (arr[mid] >= t) {
                ans = mid;
                e = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return ans;
    }
}
