package victor.training.patterns.creational.singleton;

public class UnicaInegalabila { // eg config, connec pool,
   // naspa sa scrii tu getInstance: testare, multithreading
   private static UnicaInegalabila INSTANCE;// = new UnicaInegalabila(); // eager
   private String oSetare;

   private UnicaInegalabila() {
      readSettings();
   }

   public static UnicaInegalabila getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new UnicaInegalabila();
      }
      return INSTANCE;
   }

   private void readSettings() {
      // iei de undeva setarea, cu sudoare.
      // apel in DB, citire din fisier,
      System.out.println("VAD O SINGURA DATA INITIALARE");
      oSetare = "citita din DB";
   }

   public String getoSetare() {
      return oSetare;
   }
}
