package victor.training.patterns.structural;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CheckedNU {

   public void caller() throws FileNotFoundException {
      method();
   }


   public void method() throws FileNotFoundException {// checked exceptions sunt un "abstraction leak" - tradeaza implementare
      new FileOutputStream("a.txt");
   }
}


class MyException extends RuntimeException {
   private final ErrorCode errorCode;

   MyException(ErrorCode errorCode) {
      this.errorCode = errorCode;
   }

   enum ErrorCode {
      FILE_NOT_FOUND,
      DB_DOWN
   }
}