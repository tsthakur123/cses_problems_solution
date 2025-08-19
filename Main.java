import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        FastReader fr=new FastReader();
        PrintWriter out=new PrintWriter(System.out);
        long n=fr.nextLong();
        while(true){
            out.print(n + " ");
            if(n==1) break;
            if(n%2==0){
                n/=2;
            }else{
                n=3*n+1;
            }
        }
        out.println();
        out.flush();
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
    }
}