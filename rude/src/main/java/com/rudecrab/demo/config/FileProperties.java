/**
 * Copyright (C), 2020, XXX公司
 * FileName: FileProperties
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.rudecrab.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-12 21:25
 */

@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
