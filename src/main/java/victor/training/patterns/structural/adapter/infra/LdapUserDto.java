package victor.training.patterns.structural.adapter.infra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LdapUserDto {
	private String uId;
	private String firstName;
	private String lastName;
	private Date creationDate;
	private String workEmail;
	private List<LdapUserPhone> emailAddresses = new ArrayList<>();

	public final String getuId() {
		return uId;
	}

	public final void setuId(String uId) {
		this.uId = uId;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final Date getCreationDate() {
		return creationDate;
	}

	public final void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public final String getWorkEmail() {
		return workEmail;
	}
	public final void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public final List<LdapUserPhone> getEmailAddresses() {
		return emailAddresses;
	}
	public final void setEmailAddresses(List<LdapUserPhone> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	
}
