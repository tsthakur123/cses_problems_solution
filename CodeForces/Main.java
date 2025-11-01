import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n=Integer.parseInt(br.readLine().trim());
            String[] arr=br.readLine().split(" ");
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<n;i++){
                map.put(Integer.parseInt(arr[i]),i+1);
            }
            int i=1, j=n;
            int mx=n;
            int mn=1;
            int mxIdx=map.get(mx);
            int mnIdx=map.get(mn);
            boolean found=false;
            while(i<j){
                if((i==mxIdx&&j==mnIdx)||(i==mnIdx&&j==mxIdx)){
                    i++;
                    j--;
                    mx--;
                    mn++;
                    mnIdx=map.get(mn);
                    mxIdx=map.get(mx);
                }else if(mxIdx==i||mxIdx==j){
                    mx--;
                    if(mxIdx==i){
                        i++;
                    }else{
                        j--;
                    }
                    mxIdx=map.get(mx);
                }else if(mnIdx==i||mnIdx==j){
                    mn++;
                    if(mnIdx==i){
                        i++;
                    }else{
                        j--;
                    }
                    mnIdx=map.get(mn);
                }else{
                    System.out.println(i+" "+j);
                    found=true;
                    break;
                }
            }
            if(!found) System.out.println(-1);
        }
    }
}
