package common;

import javax.mail.PasswordAuthentication;

public class SMTPAuthentication extends javax.mail.Authenticator{

    public PasswordAuthentication getPasswordAuthentication(){

    	String username = "";
        String password = "";

        return new PasswordAuthentication(username, password);

    }

}