package com.example.demo.email;


import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMinmeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, encoding:"utf-8");
            helper.setText(email, html:true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("hello@hotmail.com");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
