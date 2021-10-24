package berryStreet.bluering.backend.Utils;


import berryStreet.bluering.backend.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailUtil {
    @Autowired
    JavaMailSenderImpl mailSender;
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    public boolean sendMail(Mail mail) {
        try {
            checkMail(mail);
            sendMimeMail(mail);
        } catch (Exception e) {
            mail.setStatus(MailUtil.FAIL);
            mail.setError(e.getMessage());
        } finally {
            return mail.getStatus().equals(MailUtil.SUCCESS);
        }
    }

    private void checkMail(Mail mail) {
        if (StringUtils.isEmpty(mail.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mail.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mail.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    private void sendMimeMail(Mail mail) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型
            messageHelper.setFrom(mail.getFrom());//邮件发信人
            messageHelper.setTo(mail.getTo().split(","));//邮件收信人
            messageHelper.setSubject(mail.getSubject());//邮件主题
            messageHelper.setText(mail.getText());//邮件内容
            if (!StringUtils.isEmpty(mail.getCc())) {//抄送
                messageHelper.setCc(mail.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mail.getBcc())) {//密送
                messageHelper.setCc(mail.getBcc().split(","));
            }
            if (mail.getMultipartFiles() != null) {//添加邮件附件
                for (MultipartFile multipartFile : mail.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (StringUtils.isEmpty(mail.getSentDate().toString())) {//发送时间
                mail.setSentDate(new Date());
                messageHelper.setSentDate(mail.getSentDate());
            }
            MimeMessage mimeMessage = messageHelper.getMimeMessage();
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
            mail.setStatus(MailUtil.SUCCESS);
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }

}
