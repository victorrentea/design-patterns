package victor.training.oo.structural.hack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class CumHacuiOClasaPtUnClient implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CumHacuiOClasaPtUnClient.class);
    }


    @Autowired
    private Functionalitate inocenta;

    @Override
    public void run(String... args) throws Exception {
        inocenta.m();
    }
}

@Service
class Inocenta implements Functionalitate {
    // originala
    @Override
    public void m() {
        System.out.println("Rugaciune");
    }
}

@Service
@Primary
@Profile("clientul1")
class Alalalt implements Functionalitate {
    @Override
    public void m() {
        System.out.println("Voodoo");
    }
}