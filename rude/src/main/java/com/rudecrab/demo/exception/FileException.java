/**
 * Copyright (C), 2020, XXX公司
 * FileName: FileException
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.rudecrab.demo.exception;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-12 21:23
 */
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
