package victor.training.patterns.structural.facade.entity;

import lombok.Data;

@Data
public class Email {
	private String to;
	private String from;
	private String subject;
	private String body;
}
