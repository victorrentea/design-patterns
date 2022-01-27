package victor.training.patterns.structural.adapter.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


/// web module
@RestController
public class TheController { // why do you need a controller
   private final ServiceImplemOnBE serviceImplemOnBE;

   public TheController(ServiceImplemOnBE serviceImplemOnBE) {
      this.serviceImplemOnBE = serviceImplemOnBE;
   }

//   public void doStuff(Dto) {
//      MyEntity e = fromDto(dto)
//      serviceImplemOnBE.doStuff(reqData);
//   }
}

/// -------- logic module ------------
@Service
class ServiceImplemOnBE implements AManuallyCraftedInterface {
   //SaladRepo saladRepo;
   @Override
   public void doStuff(SaladDto dto) { // the ENEMY
//      Salad salad = SaladBuiler.withththththt (dto).build();
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /
      // domain logic /

   }
}

class ServiceClientUsedByClientApps implements AManuallyCraftedInterface {

   @Override
   public void doStuff(SaladDto salad) {
      // rest call to controller via REST
   }
}

class SaladDto {
}

// API module
interface AManuallyCraftedInterface {
   void doStuff(SaladDto salad);
}
// ---  modules ---
// api: interfaces + DTOs


// ------------------------
//Entity are private to logic module
//Entity are persisted to DB (@Document / jooq persisted)
//Converted to DTOs when sent via API jar