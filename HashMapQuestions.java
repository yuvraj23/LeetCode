import java.util.HashMap;
import java.util.Map;

public class HashMapQuestions {
    public static void main(String a[]){
        highestFrequencyCharacter("aabbcccde");

    }

    public static void highestFrequencyCharacter(String w){
        int max=0;
        String maxChar="";
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<w.length();i++){
            if(map.containsKey(w.charAt(i)+"")){
                int v=map.get(w.charAt(i)+"")+1;
                map.put(w.charAt(i)+"",v);
                if(v>max){
                    max=v;
                    maxChar=w.charAt(i)+"";
                }
            }else{
                map.put(w.charAt(i)+"",1);
                if(max==0){
                    max=1;
                    maxChar=w.charAt(i)+"";
                }
            }
        }
        System.out.println(maxChar+" = "+max);

    }
}
