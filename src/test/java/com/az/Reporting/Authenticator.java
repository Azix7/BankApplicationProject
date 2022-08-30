package com.az.Reporting;



import javax.mail.PasswordAuthentication;

// TODO: Auto-generated Javadoc
/**
 * The Class Authenticator.
 */
public class Authenticator extends javax.mail.Authenticator {

	/** The authentication. */
	private PasswordAuthentication authentication;

	/**
	 * Instantiates a new authenticator.
	 * 
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 */
	public Authenticator(String username, String password) {
		authentication = new PasswordAuthentication(username, password);
	}

	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		return authentication;
	}
}

