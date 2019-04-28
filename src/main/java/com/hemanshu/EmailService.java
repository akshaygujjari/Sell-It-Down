package com.hemanshu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;
@Service
public class EmailService {
	private JavaMailSender sender;
	
	@Autowired
	public EmailService(JavaMailSender sender) {
		this.sender=sender;
	}
	
	public void sendEmail(List<User> users,String text,String subject) throws Exception{
//		System.out.println("before");
		        MimeMessage message = sender.createMimeMessage();
//		        System.out.println("after");
		        for (User user1 : users) {
		        	
		        try {
		
		        // Enable the multipart flag!
		
		        MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		         
		        System.out.println(user1.getEmailID());
		       helper.setFrom("team2@sellitdown.com");
		        helper.setTo(user1.getEmailID());
		
		       helper.setText(text);
		
		        helper.setSubject(subject);
		         
		
		        sender.send(message);
		        }
		        catch(Exception e){
		        	System.out.println("email not send to "+user1.getEmailID());
		        	}
		        }
		            
		        
		    }

	public void emailFriend(String friend,String text,String subject, String from) throws Exception{
//		System.out.println("before");
		MimeMessage message = sender.createMimeMessage();
//		        System.out.println("after");
		String[] users = friend.split(",");
		for (int i=0; i<users.length; i++){



			// Enable the multipart flag!

			MimeMessageHelper helper = new MimeMessageHelper(message,true);


//		        System.out.println(user1.getEmailID());
			helper.setFrom("team2@sellitdown.com");
			System.out.println("Sending message from " + from);
			helper.setTo(users[i]);
			System.out.println("Sending message to " + users[i]);
			helper.setText(text);

			helper.setSubject(subject);


			sender.send(message);
			System.out.println("\n\n\n***************\n Sent Message to "+friend+"\n\n********************\n\n");
		}
	}

}
