package victor.training.patterns.adapter.infra;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class LdapUserDto {
	@JsonProperty("uID")
	private String username;
	@JsonProperty("fName")
	private  String fName;
	private  String lName;
	@NotNull
	private  String workEmail;

//	public LdapUserDto() {}

	public String getFullName() {
		return fName +" " + lName.toUpperCase();
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public boolean hasEmail() {
		return workEmail != null;
	}
}
