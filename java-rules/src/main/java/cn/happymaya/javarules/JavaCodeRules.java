package cn.happymaya.javarules;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCodeRules {

    public void reduceVariableScope(String str) {
        final int a = 100;
        if (StringUtils.isEmpty(str)) {
            int b = a * a;
        }
    }

    public String stringConcatenationUsingStringBuilderOrStringBuffer() {
        String str = "-1";
        for (int i = 0; i < 10; i++) {
            str += i;
        }
        return str;
    }

    // BAD
    public String test1(List<String> list, int index) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    // GOOD
    public String test2(List<String> list, int index){
        if (index >= list.size() || index <=0 ) {
            return null;
        }
        return list.get(index);
    }

    public static void main(String[] args) {
        int a = 2;
        int b = (a++) << (++a) + (++a);
        System.out.println(b);

//        Pattern pattern = Pattern.compile({pattern});
//        Matcher pattern = Pattern.matches({content});
    }
}
