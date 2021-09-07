package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.service.NotificationService;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final NotificationService notificationService;

	public void checkStock() {
		// 3 lines of domain Logic

		// TODO same as in CustomerFacade but with other subject/body

		notificationService.sendStockCheckedEmail("a@b.com");
//		notificationService.sendEmail("a@b.com", "Your product is in stock! Hooray!", "Incredible!");
	}
}
