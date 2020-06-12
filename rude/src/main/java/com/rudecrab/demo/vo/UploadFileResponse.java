/**
 * Copyright (C), 2020, XXX公司
 * FileName: UploadResponse
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.rudecrab.demo.vo;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-12 21:30
 */
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
    // getter and setter ...
}
