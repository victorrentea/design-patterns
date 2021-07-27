package victor.training.patterns.structural.adapter.infra;

import java.util.Arrays;
import java.util.Date;

public class DummyData {
	public static final LdapUserDto ldapUser1 = new LdapUserDto();

	static {
		ldapUser1.setFirstName("John");
		ldapUser1.setLastName("DOE");
		ldapUser1.setuId("jdoe");
		ldapUser1.setCreationDate(new Date());
		ldapUser1.setWorkEmail("0123456789");
		ldapUser1.setEmailAddresses(Arrays.asList(new LdapUserPhone("WORK", "jdoe@bigcorp.com")));
	}

	public static final LdapUserDto ldapUser2 = new LdapUserDto();

	static {
		ldapUser2.setFirstName("Jane");
		ldapUser2.setLastName("DOE");
		ldapUser2.setuId("janedoe");
		ldapUser2.setCreationDate(new Date());
	}
}
