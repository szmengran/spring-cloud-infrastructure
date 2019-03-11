package com.szmengran.config.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * File Name:ResouceController.java 
 * Package Name:com.szmengran.config.controller 
 * @TODO:
 * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
 * Copyright (c) 2018, 深圳市梦燃科技有限公司 All Rights Reserved. 
 * @createTime 2018年9月4日上午12:32:52
 */
@RestController
public class ResouceController {

    /**
     * download file
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws Exception 
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @GetMapping("/resources/{fileName}")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws Exception {
        if (null != fileName) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            InputStream in = null; 
            BufferedInputStream bis = null;
            try {
                in = getClass().getResourceAsStream("/"+fileName); 
                bis = new BufferedInputStream(in);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != bis) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != in) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}

