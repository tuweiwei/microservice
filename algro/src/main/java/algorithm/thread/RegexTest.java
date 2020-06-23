/**
 * Copyright (C), 2020, XXX公司
 * FileName: RegexTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm.thread;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-23 20:12
 */
public class RegexTest {
    public static void main(String[] args) {
        // >>> 高位补零   >> 高位补 符号位    << 左移    异或相同为 0
//        int a = 1100; int b = 2;
//        System.out.println(a << b);

        //   \后面的字符不会看作meta character  \w 字母数字下划线 \s空格字符（空格键、tab、换行、换页、回车）
        //[a-d[m-p]] a 到 d 或 m 到 p：[a-dm-p]（并集）
        //[a-z&&[def]] d、e 或 f（交集）
        //[a-z&&[^bc]] a 到 z，除了 b 和 c：[ad-z]（减去）
        //[a-z&&[^m-p]] a 到 z，而非 m 到 p：[a-lq-z]（减去）

        String regex = "\\w(\\d\\d)(\\w+)";
        //String candidate = "x99SuperJava y88Java";
        String candidate = "Java's regex package is the theme of this article";
        //Pattern p = Pattern.compile(regex);
        Pattern p = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
        Matcher m = p.matcher(candidate);
        while(m.find()){
            System.out.println("aaa");
            int gc = m.groupCount();
            System.out.println(gc);
            for(int i = 0; i <= gc; i++)
                System.out.println(m.group());
        }

    }
}
