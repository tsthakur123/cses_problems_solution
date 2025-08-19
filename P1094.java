import java.util.*;
import java.io.*;

public class P1094 {
    public static void main(String[] args) throws IOException {
        FastReader fr=new FastReader();
        int n=fr.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=fr.nextInt();
        long steps=0;
        for(int i=1;i<n;i++){
            if(arr[i-1]>arr[i]){
                steps+=arr[i-1]-arr[i];
                arr[i]=arr[i-1];
            }
        }
        System.out.println(steps);
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
        int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
    }
}
