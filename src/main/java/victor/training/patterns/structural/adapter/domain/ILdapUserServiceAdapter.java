package victor.training.patterns.structural.adapter.domain;

public interface ILdapUserServiceAdapter {
   User findOneByUsername(String username);
}
