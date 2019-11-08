package victor.training.oo.behavioral.command;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceActivatorPattern {
    private final Source source;

    public ServiceActivatorPattern(Source source) {
        this.source = source;
    }
    public void askInParallel() {
        log.info("Sending messages");
        //source.output().send(MessageBuilder.withPayload(...).build());
        log.info("Sent messages");
    }
}

enum DrinkType {
    BEER, VODKA
}

@Slf4j
@MessageEndpoint
class MessageHandler {
    private final Barman barman;
    public MessageHandler(Barman barman) {
        this.barman = barman;
    }

    //@ServiceActivator(inputChannel = Sink.INPUT)
    // TODO check parallelism
    public void handleOrder(DrinkType drinkType) {
        log.info("Executing order for {} in handler {}", drinkType, this);
        Object drink = callBarman(drinkType);
        log.info("Got drink: " + drink);
    }

    private Object callBarman(DrinkType drinkType) {
        switch (drinkType) {
            case BEER: return barman.pourBeer();
            case VODKA: return barman.pourWhiskey();
            default: throw new NotImplementedException("Unknown drink type: " + drinkType);
        }
    }
}