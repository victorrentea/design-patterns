package victor.training.patterns.behavioral.template;

import java.util.Random;

public class Email {
   private final long id = new Random(System.nanoTime()).nextLong();
   private String subject;
   private String body;
   private String sender;
   private String replyTo;
   private String to;

   public long getId() {
      return id;
   }

   public String getSubject() {
      return subject;
   }

   public Email setSubject(String subject) {
      this.subject = subject;
      return this;
   }

   public String getBody() {
      return body;
   }

   public Email setBody(String body) {
      this.body = body;
      return this;
   }

   public String getSender() {
      return sender;
   }

   public Email setSender(String sender) {
      this.sender = sender;
      return this;
   }

   public String getReplyTo() {
      return replyTo;
   }

   public Email setReplyTo(String replyTo) {
      this.replyTo = replyTo;
      return this;
   }

   public String getTo() {
      return to;
   }

   public Email setTo(String to) {
      this.to = to;
      return this;
   }
}
