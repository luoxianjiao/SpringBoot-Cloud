package com.example;

import com.example.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
/**
 * @ClassName: MailServiceTest
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2019/1/15 10:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("gototang@qq.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("gototang@163.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="d:\\fan.jpg";
        mailService.sendAttachmentsMail("gototang@163.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "d:\\fan.jpg";

        mailService.sendInlineResourceMail("gototang@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        //使用emailTemplate.html文件的模板
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("gototang@163.com","主题：这是模板邮件",emailContent);
    }
}
