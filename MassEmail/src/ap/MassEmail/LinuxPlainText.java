package ap.MassEmail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

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

	static String messageC;
	static String subject;
	static String[] splitMessage;
	static Console cnsl;
	static String username;
	static String password;
	static String[] splitUser;
	static String host;
	static BufferedReader emails;
	static BufferedReader messageSC;
	static String encoding;
	static String os;
	
	public static void main(String[] args) throws IOException {
		if(System.getProperty("os.name").toLowerCase().equals("linux")) {
			emails = new BufferedReader(new InputStreamReader(new FileInputStream("emails.txt"), "utf-8"));
			messageSC = new BufferedReader(new InputStreamReader(new FileInputStream("message.txt"), "utf-8"));
			os = "linux";
		}
		else if(System.getProperty("os.name").toLowerCase().equals("windows")) {
			emails = new BufferedReader(new InputStreamReader(new FileInputStream("emails.txt"), "Cp1252"));
			messageSC = new BufferedReader(new InputStreamReader(new FileInputStream("message.txt"), "Cp1252"));
			os = "windows";
		}
		cnsl = null;
		try{
	         cnsl = System.console();
	         if (cnsl != null) {
	        	System.out.print("Sender Email: ");
	            username = cnsl.readLine();
	            System.out.print("Password: ");
	            char[] pwd = cnsl.readPassword();
	            password = new String(pwd);
	            System.out.println();
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
	
		Transport transport = null;
		try {
			transport = session.getTransport("smtp");
			transport.connect(host, username, password);
		} catch (NoSuchProviderException e2) {
			e2.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		String line = emails.readLine();
		while(line != null) {
			String[] split = line.split("-");
			messageC = new String();
			if (os.equals("linux")) {
				messageSC = new BufferedReader(new InputStreamReader(new FileInputStream("message.txt"), "utf-8"));
				subject = messageSC.readLine();
				String nextLineMess = messageSC.readLine();
				while (nextLineMess != null) {
					messageC = messageC + "\n" + nextLineMess;
					nextLineMess = messageSC.readLine();
				}
				splitMessage = messageC.split("recipient_name");
				messageC = new String();
				for (int i = 0; i < splitMessage.length; i++) {
					if (splitMessage.length != (i + 1)) {
						messageC = messageC + splitMessage[i] + split[0];
					} else {
						messageC = messageC + splitMessage[i];
					}
				}
			}
			else if (os.equals("linux")) {
				messageSC = new BufferedReader(new InputStreamReader(
						new FileInputStream("message.txt"), "Cp1252"));
				subject = messageSC.readLine();
				String nextLineMess = messageSC.readLine();
				while (nextLineMess != null) {
					nextLineMess = messageSC.readLine();
					messageC = messageC + "\n" + nextLineMess;
					nextLineMess = messageSC.readLine();
				}
				splitMessage = messageC.split("recipient_name");
				messageC = new String();
				for (int i = 0; i < splitMessage.length; i++) {
					if (splitMessage.length != (i + 1)) {
						messageC = messageC + splitMessage[i] + split[0];
					} else {
						messageC = messageC + splitMessage[i];
					}
				}
			}
			else {System.out.println("Coding Error! Sorry :(");}
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
			System.out.println("Done\n");
			line = emails.readLine();
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("All messages sent!");
	}

}
