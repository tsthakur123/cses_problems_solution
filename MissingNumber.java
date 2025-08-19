import java.util.*;
import java.io.*;

public class MissingNumber {
    public static void main(String[] args) throws IOException{
        FastReader fr=new FastReader();
        int n=fr.nextInt();
        long expectedSum = ((long)n * (n + 1)) / 2;
        long actualSum=0;
        for(int i=0;i<n-1;i++){
            actualSum+=fr.nextLong();
        }
        System.out.println(expectedSum-actualSum);
    }
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next() throws IOException{
            while(st==null||!st.hasMoreElements()){
                st=new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }
        long nextLong() throws IOException{
            return Long.parseLong(next());
        }
        int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
    }
}
