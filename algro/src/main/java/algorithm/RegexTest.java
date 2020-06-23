/**
 * Copyright (C), 2020, XXX公司
 * FileName: RegexTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-23 20:12
 */
public class RegexTest {
    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoobkkk";
    private static String REPLACE = "-";

    public static void main(String[] args) {
//        共找到 4 处匹配：
//        aab
//        aab
//        ab
//        b
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        System.out.println(m.matches());
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            // 把匹配到的 替换 -
            m.appendReplacement(sb,REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());





        // >>> 高位补零   >> 高位补 符号位    << 左移    异或相同为 0
//        int a = 1100; int b = 2;
//        System.out.println(a << b);

        //   \后面的字符不会看作meta character  \w 字母数字下划线 \s空格字符（空格键、tab、换行、换页、回车）
        //[a-d[m-p]] a 到 d 或 m 到 p：[a-dm-p]（并集）
        //[a-z&&[def]] d、e 或 f（交集）
        //[a-z&&[^bc]] a 到 z，除了 b 和 c：[ad-z]（减去）
        //[a-z&&[^m-p]] a 到 z，而非 m 到 p：[a-lq-z]（减去）

//        String regex = "\\w(\\d\\d)(\\w+)";
//        //String candidate = "x99SuperJava y88Java";
//        String candidate = "Java's regex package is the theme of this article";
//        //Pattern p = Pattern.compile(regex);
//        Pattern p = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
//        Matcher m = p.matcher(candidate);
//        while(m.find()){
//            System.out.println("aaa");
//            int gc = m.groupCount();
//            System.out.println(gc);
//            for(int i = 0; i <= gc; i++)
//                System.out.println(m.group());
//        }

    }

    private void ss(){

        String REGEX = "dog";
        String INPUT = "The dog says meow. " + "All dogs say meow.";
        String REPLACE = "cat";

        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);

        //The cat says meow. All cats say meow.
    }
}
