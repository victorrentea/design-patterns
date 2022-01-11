# Assignment

## Task

Try to introduce every single Design Pattern you can think of. The goal is to experiment and play.

Disclaimer: I would discourage such an attitude in production systems, but for learning, try to add in here all the
patterns you can imagine.

If lacking ideas, consider:

- **Adapter** + a new Domain class to return from it
- **Repository** encapsulating the (very ugly) raw JDBC access
- Exposing **Dtos** from your REST API (@GetMapping method), instead of central Entities
- Isolate the filtering rules in a class that only works on structures that I control (that is, in a **Domain Service**)
- Introduce a **Facade** to orchestrate the main flow (3-5 lines in its method)
- **Factory** method
- **Decorator** around the extracted Adapter that logs every method call
- **Proxy** if you are brave enough to try implement a Spring @Aspect (with the same goal: logging method calls +
  parameters) [hard]
- "Manual" **singleton** around the configuration (despite the wonderful Spring @Value annotation) to avoid re-reading
  it every time
- Fluent **Builder** for the Lemon to ease its construction when read from DB
- **Observer** Pattern to insert the audit "out of band", without direct coupling of our code to it
- **Command** Pattern/DTO to encapsulate the two input parameters as an object
- **Filters** that remove (or allow) a Lemon to pass the search criteria