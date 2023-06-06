package Utils;

import java.math.BigDecimal;

public class StringCope {
    public static void main(String[] args) {
        format(new StringBuffer("50000"));
    }
    public static String format(StringBuffer s){
        int i = s.indexOf(".");
        if(i==-1){
            s.append(".00");
        }
        i = s.indexOf(".");
        for(int j = i-3;j>0;j-=3)
            s = s.insert(j,',');
        return s.toString();
    }
}
