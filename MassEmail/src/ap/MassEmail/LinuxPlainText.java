package ap.MassEmail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Console;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LinuxPlainText {

	static long lines;
	static Scanner emails;
	static Scanner messageSC;
	static String messageC;
	static String subject;
	static String[] splitMessage;
	static Scanner infoReader;
	static Console cnsl;
	static String username;
	static String password;
	static String[] splitUser;
	static String host;
	
	public static void main(String[] args) {
		cnsl = null;
		try{
	         cnsl = System.console();

	         if (cnsl != null) {
	        	 System.out.print("Sender Email: ");
		         username = cnsl.readLine();
		         System.out.print("Password: ");
		         char[] pwd = cnsl.readPassword();
		         password = new String(pwd);
		         System.out.println("");
	         }      
	      }catch(Exception ex){
	         ex.printStackTrace();      
	      }
		
		splitUser = username.split("@");
		Properties props = new Properties();
		if (splitUser[1].equals("yahoo.com")) {
			host = "smtp.mail.yahoo.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		}
		else if (splitUser[1].equals("comcast.net")) {
			host = "smtp.comcast.net";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		}
		else if(splitUser[1].equals("hotmail.com") || splitUser[1].equals("outlook.com") || splitUser[1].equals("live.com")) {
			host = "smtp-mail.outlook.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		}
		else if(splitUser[1].equals("aol.com")) {
			host = "smtp.aol.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		} 
		else if(splitUser[1].equals("aim.com")) {
			host = "smtp.aim.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		} 
		else if(splitUser[1].equals("icloud.com")) {
			host = "smtp.mail.me.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		} 
		else {
			host = "smtp.gmail.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.password", password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", "587");
		}
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		
		try {
			emails = new Scanner(new File("emails.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Transport transport = null;
		try {
			transport = session.getTransport("smtp");
			transport.connect(host, username, password);
		} catch (NoSuchProviderException e2) {
			e2.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		while(emails.hasNextLine()) {
			String line = emails.nextLine();
			String[] split = line.split("-");
			
			messageC = new String();
			try {
				messageSC = new Scanner(new File("message.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			subject = messageSC.nextLine();
			
			while(messageSC.hasNextLine()) {
				messageC = messageC + "\n" + messageSC.nextLine();
			}
			splitMessage = messageC.split("recipient_name");
			messageC = new String();
			
			for (int i = 1; i < splitMessage.length; i++) {
				if (splitMessage.length != (i + 1)) {
					messageC = messageC + splitMessage[i] + split[0];
				}
				else{
					messageC = messageC + splitMessage[i];
				}
			}
			
			try {
				System.out.println("Sending message to "+split[0]);
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(split[1]));
				System.out.println("Set email as " + split[1]);
				message.setSubject(subject);
				message.setContent(messageC, "text/plain");
			    transport.sendMessage(message, message.getAllRecipients());
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Done");
			System.out.println();
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("All messages sent!");
	}

}
