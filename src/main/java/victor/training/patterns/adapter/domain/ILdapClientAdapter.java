package victor.training.patterns.adapter.domain;

import victor.training.patterns.adapter.infra.LdapUserDto;

public interface ILdapClientAdapter {
    LdapUserDto findByUsername(String username);
}
