package victor.training.patterns.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.structural.facade.Facade;
import victor.training.patterns.structural.facade.service.EmailService;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final EmailService emailService;

	public void checkStock() {
		// 3 lines of domain Logic

		// TODO De la biz citire: send email similar to that on register cutsomer but with other subject/body
		emailService.sendEmail("a@b.com", "Surpriza!", "Vesti bune anul are: A fost in stock!");
	}

}
