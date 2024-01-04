package com.create.user.portlet;

public class LiferayUser {
	
	private String alternateName;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String password;
	public String getalternateName() {
		return alternateName;
	}
	public void setalternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LiferayUser [alternateName=" + alternateName + ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", jobTitle=" + jobTitle + ", password=" + password + "]";
	}
	
	

}
