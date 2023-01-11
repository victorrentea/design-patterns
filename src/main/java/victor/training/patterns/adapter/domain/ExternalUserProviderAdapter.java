package victor.training.patterns.adapter.domain;

import victor.training.patterns.adapter.infra.LdapUserApiClient;
import victor.training.patterns.adapter.infra.LdapUserDto;

import java.util.List;

// ghena
public class ExternalUserProviderAdapter {

private final LdapUserApiClient apiClient;

  public ExternalUserProviderAdapter(LdapUserApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public User fetchUserByUsername(String username) {
    List<LdapUserDto> list = apiClient.search(username.toUpperCase(), null, null);

    if (list.size() != 1) {
      throw new IllegalArgumentException("There is no single user matching username " + username);
    }

    LdapUserDto ldapUser = list.get(0);
    String fullName = ldapUser.getfName() + " "
                      + ldapUser.getlName().toUpperCase(); // mapping spread in my domain

    User user = new User(
            ldapUser.getuId(),
            ldapUser.getWorkEmail(),
            fullName
    );
    return user;
  }
}
