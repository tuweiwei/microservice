/**
 * Copyright (C), 2020, XXX公司
 * FileName: POIUtil
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.zhangxiaoxi;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-18 00:11
 */
public class POIUtil {

    public static <T> List<String> readWordFile(String path) {
        List<String> contextList = Lists.newArrayList();
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(path));
            if (path.endsWith(".doc")) {
                HWPFDocument document = new HWPFDocument(stream);
                WordExtractor extractor = new WordExtractor(document);
                String[] contextArray = extractor.getParagraphText();
                Arrays.asList(contextArray).forEach(
                        context -> contextList.add(CharMatcher.JAVA_WHITESPACE.removeFrom(context)));
                extractor.close();
                document.close();
            } else if (path.endsWith(".docx")) {
                XWPFDocument document = new XWPFDocument(stream).getXWPFDocument();
                List<XWPFParagraph> paragraphList = document.getParagraphs();
                paragraphList.forEach(paragraph -> contextList.add(CharMatcher.JAVA_WHITESPACE.removeFrom(paragraph.getParagraphText())));
                document.close();
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != stream) try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contextList;
    }

    public static void main(String[] args) throws InterruptedException {
//        String path = "C:\\Users\\tuwei\\Desktop\\rusume\\2.docx";
//        List<String> strings = readWordFile(path);
//        System.out.println(strings);

//        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
//        Thread putThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("put thread start");
//                try {
//                    queue.put(1);
//                } catch (InterruptedException e) {
//                }
//                System.out.println("put thread end");
//            }
//        });
//
//        Thread takeThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("take thread start");
//                try {
//                    System.out.println("take from putThread: " + queue.take());
//                } catch (InterruptedException e) {
//                }
//                System.out.println("take thread end");
//            }
//        });
//
//        putThread.start();
//        Thread.sleep(1000);
//        takeThread.start();







    }//end main func


}
