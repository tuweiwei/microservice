/**
 * Copyright (C), 2020, XXX公司
 * FileName: Utils
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.rudecrab.demo.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-12 21:53
 */
public class Utils {
    public static void main(String[] args) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("sURL");

        //FileBody fileBody = new FileBody(new File(""));


        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        //builder.addPart("media", new FileBody(new File("")));

        builder.addTextBody("text", "111", ContentType.TEXT_PLAIN);
        builder.setCharset(Charset.forName("utf8"));
        // 把文件加到HTTP的post请求中
        File file = new File("filePath://path");
        builder.addBinaryBody(
                "img",
                new FileInputStream(file),
                ContentType.APPLICATION_OCTET_STREAM,
                file.getName()
        );

        HttpEntity multipart = builder.build();

        post.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity responseEntity = response.getEntity();

        Header header = responseEntity.getContentType();
        //打印内容
        String sResponse= EntityUtils.toString(responseEntity, "UTF-8");//5、获取网页内容，并且指定编码
        System.out.println("Post 返回结果"+sResponse);
        httpClient.close();
        response.close();
    }
}
