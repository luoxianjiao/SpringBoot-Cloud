package com.example.api.consumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

/**
 * @ClassName: ApplicationTest
 * @Description: 编写测试用例来通过定义的Feign客户端来传文件
 * @Author Comsys-xianjiao.luo
 * @Date 2018/12/25 17:49
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    @SneakyThrows
    public void testFileUpload() {
        File file = new File("d:/logs/bjaf.log");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.TEXT_PLAIN_VALUE, true,file.getName());
        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("error file:"+e, e);
        }
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        String name = fileUploadService.fileUpload(multipartFile);
        System.out.println("调用上传接口，并返回结果："+name);//测试结束了,Shutdown in progress 释放资源
        while (true) {

        }


    }
}

