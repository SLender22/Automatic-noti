package Utils;

public class StringCope {
    public static String format(StringBuffer s){
        int i = s.indexOf(".");
        for(int j = i-3;j>0;j-=3)
            s = s.insert(j,',');
        return s.toString();
    }
    public static String formatNum(StringBuffer s){
        int i = s.indexOf(".");
        s = s.delete(i,s.length());
        return s.toString();
    }
}
