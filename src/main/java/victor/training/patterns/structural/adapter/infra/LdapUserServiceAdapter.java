package victor.training.patterns.structural.adapter.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.structural.adapter.domain.ILdapUserServiceAdapter;
import victor.training.patterns.structural.adapter.domain.User;

import java.util.List;

@Service
public class LdapUserServiceAdapter implements ILdapUserServiceAdapter {
   @Autowired
   LdapUserWebserviceClient wsClient;

   @Override
   public User findOneByUsername(String username) {
      List<LdapUserDto> list = wsClient.search(username.toUpperCase(), null, null);
      if (list.size() != 1) {
         throw new IllegalArgumentException("There is no single user matching username " + username);
      }
      LdapUserDto dto = list.get(0);
//      if (dto.getuId() == null) throw new IllegalArgumentException();
      return fromDto(dto);
   }

   private User fromDto(LdapUserDto ldapUser) {
      String fullName = ldapUser.getfName() + " " + ldapUser.getlName().toUpperCase();
      return new User(ldapUser.getuId(), fullName, ldapUser.getWorkEmail());
   }
}