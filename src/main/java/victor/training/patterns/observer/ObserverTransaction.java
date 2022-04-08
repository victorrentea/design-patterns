package victor.training.patterns.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class ObserverTransaction {
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private AnotherClass anotherClass;
	
	@Transactional
	public void runInTransaction() {
		publisher.publishEvent(new DeleteFilesEvent(asList("data.txt")));
		anotherClass.addFilesToBeDeleted();
		// TODO What if an exception?...
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
	public void runAfterTransaction(DeleteFilesEvent event) {
		System.out.println("Cleaning files: " + event.fileNames);

	}
}

@Component
class AnotherClass {
	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void addFilesToBeDeleted() {
		publisher.publishEvent(new DeleteFilesEvent(asList("f.txt")));
	}
}


class DeleteFilesEvent {
	public final List<String> fileNames;

	public DeleteFilesEvent(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	
}