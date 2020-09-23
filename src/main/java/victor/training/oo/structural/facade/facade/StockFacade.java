package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.domain.EmailService;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final EmailService emailService;

	public void checkStock() {
		// 3 lines of domain Logic


		emailService.sendOrderNotStockEmail("a@b.com");
	}


}
