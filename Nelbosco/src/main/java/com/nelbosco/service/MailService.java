package com.nelbosco.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nelbosco.domain.MailDTO;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "넬보스코체크 <hypark023@osse.co.kr>";

    public void mailSend(MailDTO mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }

	public void sendMail(String recAddress, String subject, String content) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(recAddress);
			message.setFrom(MailService.FROM_ADDRESS);
			message.setSubject(subject);
			message.setText(content);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}