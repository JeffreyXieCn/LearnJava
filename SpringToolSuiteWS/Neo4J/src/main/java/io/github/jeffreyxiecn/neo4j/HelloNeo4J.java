package io.github.jeffreyxiecn.neo4j;

import static org.neo4j.driver.v1.Values.parameters;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

public class HelloNeo4J implements AutoCloseable {
  private final Driver driver;

  public HelloNeo4J(String uri, String user, String password) {
    driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
  }

  @Override
  public void close() throws Exception {
    driver.close();
  }

  public void printGreeting(final String message) {
    try (Session session = driver.session()) {

      //      Map<String, String> checksumsMap = new HashMap<>();
      //      checksumsMap.put("sha1", "3b8f07c6539085d9b5db7c3aef7e3c1241bf58bf");
      //      checksumsMap.put("sha256",
      // "c0488108f33e2d491f8f3de208da8c03d7c3fb19f938bccd7b9a11ae5290114c");
      String greeting =
          session.writeTransaction(
              new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                  StatementResult result =
                      tx.run(
                          "CREATE (a:Greeting) "
                              + "SET a.message = $message "
                              // + "SET a.checksums = $chksums "
                              + "RETURN a.message + ', from node ' + id(a)",
                          parameters("message", message));
                  // parameters("message", message, "chksums", checksumsMap));
                  return result.single().get(0).asString();
                }
              });
      System.out.println(greeting);
    }
  }

  public static void main(String... args) throws Exception {
    try (HelloNeo4J greeter = new HelloNeo4J("bolt://localhost:7687", "neo4j", "plms")) {
      greeter.printGreeting("hello, world");
    }
  }
}
