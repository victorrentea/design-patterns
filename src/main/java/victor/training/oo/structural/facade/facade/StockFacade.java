package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.entity.Email;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.infra.EmailClient;
import victor.training.oo.structural.facade.repo.EmailRepository;

@Facade
@RequiredArgsConstructor
public class StockFacade {

	public void checkStock() {
		// 3 lines of domain Logic

		// TODO same as in CustomerFacade but with other subject/body
		
	}
}
