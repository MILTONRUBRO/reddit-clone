package br.com.devmos.reddit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import br.com.devmos.reddit.exceptions.SpringRedditException;
import br.com.devmos.reddit.models.NotificationEmail;

@Service
public class MailService {
	
	private final JavaMailSender mailSender;
	private final MailContentBuilder mailContentBuilder;
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	public MailService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}
	
	public void sendEmail(NotificationEmail notificationEmail) {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("springreddit@email");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(notificationEmail.getBody());
		};
		
		try {
			mailSender.send(messagePreparator);
			logger.info("Activation email sent!!");
		}catch(MailException e) {
			logger.error("Exception occurred when sending mail", e);
			throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
		}
	}


}
