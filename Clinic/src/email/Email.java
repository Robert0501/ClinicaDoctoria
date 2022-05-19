package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import database.Database;
import view.NewPacientView;

public class Email {

	public static int index;
	public static EmailThread email[] = new EmailThread[100];
	public static int position = 0;

	public Email() {
		for (int i = 0; i < 100; i++) {
			email[i] = new EmailThread();
		}
	}

	public static void sendMail(String recepient) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myAccountEmail = "clinicadoctoria@gmail.com";
		String myPassowrd = "QwertyQwerty1!";

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, myPassowrd);
			}
		});
		session.setDebug(true);

		try {
			switch (index) {
			case 1:
				Message newAccountMessage = newAccountEmail(session, myAccountEmail, recepient);
				Transport.send(newAccountMessage);
				break;
			case 2:
				Message confirmedAccount = confirmedAccount(session, myAccountEmail, recepient);
				Transport.send(confirmedAccount);
				break;
			case 3:
				Message forgotPasswordEmail = forgotPasswordEmail(session, myAccountEmail, recepient);
				Transport.send(forgotPasswordEmail);
				break;
			case 4:
				Message newPacientEmail = newPacientEmail(session, myAccountEmail, recepient);
				Transport.send(newPacientEmail);
				break;
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static Message newAccountEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Welcome to Clinica Doctoria");
			message.setText("Welcome Dr. " + Database.getDoctorName(recepient) + "\nYour confirmation code is: "
					+ Database.getActivationCode(recepient)
					+ "\nThe confirmation code will be required next time you will login.\n"
					+ "We will ask for it just once in order to activate your account.\n\nThank you!\nHave a nice day!\n"
					+ "Clinica Doctoria team");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Message confirmedAccount(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Confirmed Account");
			message.setText("Dear Dr. " + Database.getDoctorName(recepient)
					+ "\nYour account has been activated. You can login now.\n"
					+ "We will not ask for the confirmation code again.\nHave a nice day!\n" + "Clinica Doctoria team");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Message forgotPasswordEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Confirmed Account");
			message.setText("Dear Dr. " + Database.getDoctorName(recepient)
					+ "\nWe are sorry to hear that you forgot your password\n"
					+ "We will help you to change it with a new one.\n"
					+ "In order to change it, you have to insert the code below in the app.\n" + "Your code is:"
					+ Database.getForgotPasswordCode(recepient) + "\nHave a nice day!\n" + "Clinica Doctoria team");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Message newPacientEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("New Account");
			message.setText("Welcome " + NewPacientView.firstNameIn.getText() + " " + NewPacientView.lastNameIn.getText() + ",\n"
					+ "Your confirmation code is: " + Database.getActivationCode(recepient)
					+ "\nThe confirmation code will be required next time you login\n"
					+ "We will ask for it just once in order to activate your account."
					+ "\nIn order to login you need to use your email as Username and First Name + Last Name as your password"
					+ "\nThe first time you login you will be asked to change your pasword with a custom one."
					+ "\n\nThank you!\nHave a nice day!" + "\nClinica Doctoria team");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
