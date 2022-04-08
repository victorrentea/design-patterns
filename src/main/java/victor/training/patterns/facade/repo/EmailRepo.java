package victor.training.patterns.facade.repo;

import org.springframework.stereotype.Repository;
import victor.training.patterns.facade.entity.Email;

@Repository
public class EmailRepo {

	public boolean emailWasSentBefore(int emailHash) {
		return false; // TODO fake implementation
	}

	public void saveSentEmail(Email email) {
		// TODO
	}

}
