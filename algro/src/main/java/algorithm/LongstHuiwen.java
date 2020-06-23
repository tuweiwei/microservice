/**
 * Copyright (C), 2020, XXX公司
 * FileName: LongstHuiwen
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm;

/**
 * @program: untitled
 * @description:
 * @author: tuwei
 * @create: 2020-05-28 18:56
 */
public class LongstHuiwen {

    private static String longst(String s){
        int size = s.length();
        int start = 0;
        int maxLength = 1;

        boolean[][] dp = new boolean[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j <= i; j++){
                if (i - j <= 1){
                    dp[j][i] = s.charAt(j) == s.charAt(i);
                }else{
                    dp[j][i] = s.charAt(j) == s.charAt(i) && dp[j + 1][i - 1];
                }

                if (dp[j][i] && maxLength < i - j + 1) {
                    start = j;
                    maxLength = i - j + 1;
                }
                System.out.println(dp[j][i] + "   ");
            }

        }
        System.out.println(start);
        System.out.println(maxLength);

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String ss = "abcadefed";
        String ret = longst(ss);
        System.out.println(ret);
    }
}
