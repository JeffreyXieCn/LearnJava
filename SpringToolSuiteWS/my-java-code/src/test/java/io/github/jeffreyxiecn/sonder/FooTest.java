package io.github.jeffreyxiecn.sonder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class FooTest {

  @Test
  void whenSerializeAndDeserializeUsingJacksonToString_thenCorrect()
      throws JsonProcessingException {
    Foo foo = new Foo(1, "first", Arrays.asList("Eat", "Sleep", "Play"));
    ObjectMapper mapper = new ObjectMapper();

    String jsonStr = mapper.writeValueAsString(foo);
    log.debug("jsonStr:\n" + jsonStr);

    Foo result = mapper.readValue(jsonStr, Foo.class);
    assertEquals(foo.getId(), result.getId());
  }

  @Test
  void whenSerializeAndDeserializeUsingJacksonToStringWithOnDemandPrettyPrinter_thenCorrect()
      throws JsonProcessingException {
    Foo foo = new Foo(1, "first", Arrays.asList("Eat", "Sleep", "Play"));
    ObjectMapper mapper = new ObjectMapper();

    String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foo);
    log.debug("jsonStr in pretty print:\n" + jsonStr);

    Foo result = mapper.readValue(jsonStr, Foo.class);
    assertEquals(foo.getId(), result.getId());
  }

  @Test
  void whenSerializeAndDeserializeUsingJacksonToStringWithGlobalPrettyPrinter_thenCorrect()
      throws JsonProcessingException {
    // pretty print
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    Foo foo = new Foo(1, "first", Arrays.asList("Eat", "Sleep", "Play"));

    String jsonStr = mapper.writeValueAsString(foo);
    log.debug("jsonStr in pretty print with global configuration:\n" + jsonStr);

    Foo result = mapper.readValue(jsonStr, Foo.class);
    assertEquals(foo.getId(), result.getId());
  }

  @Test
  void whenSerializeAndDeserializeUsingJacksonToFileWithGlobalPrettyPrinter_thenCorrect()
      throws IOException {
    // pretty print
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    Foo foo = new Foo(1, "first", Arrays.asList("Eat", "Sleep", "Play"));

    mapper.writeValue(new File("src/test/resources/foo1.json"), foo);

    Foo result = mapper.readValue(new File("src/test/resources/foo1.json"), Foo.class);
    assertEquals(foo.getId(), result.getId());
  }

  @Test
  void whenReadJsonStringIntoJsonNode_thenCorrect()
      throws JsonMappingException, JsonProcessingException {
    String json = "{\"color\": \"Black\", \"type\": \"FIAT\"}";
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(json);
    String color = jsonNode.get("color").asText();
    log.debug("color from jsonNode:" + color);
  }

  @Test
  void whenReadJsonStringIntoJavaMap_thenCorrect()
      throws JsonMappingException, JsonProcessingException {
    String json = "{\"color\": \"Black\", \"type\": \"FIAT\"}";
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
    String color = map.get("color").toString();
    log.debug("color from the map:" + color);
  }

  @Test
  void whenReadJsonStringIntoJavaListOfObjects_thenCorrect()
      throws JsonMappingException, JsonProcessingException {
    String json =
        "[{\"id\": 1, \"firstName\": \"Jeffrey\"}, {\"id\": 2, \"firstName\": \"Steve\"}]";
    ObjectMapper mapper = new ObjectMapper();
    List<Foo> foos = mapper.readValue(json, new TypeReference<List<Foo>>() {});
    String firstName = foos.get(1).getFirstName();
    assertEquals("Steve", firstName);
    log.debug("firstName:" + firstName);
  }

  @Test
  void whenReadJsonFileIntoJsonNode_thenCorrect() throws JsonProcessingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(new File("src/test/resources/foos.json"));
    int size = tree.size();
    assertEquals(3, size);

    assertEquals("second", tree.get(1).get("firstName").asText());
    log.debug("tree.get(2).toString():" + tree.get(2).toString());
    Foo thirdFoo = mapper.readValue(tree.get(2).toString(), Foo.class);
    List<String> expected = Arrays.asList("Study", "Solve", "Share");
    assertEquals(expected, thirdFoo.getHobbies());
  }

  private String readResourceFileIntoString(String path) throws Exception {
    File resource = new File(path);
    return new String(Files.readAllBytes(resource.toPath()));
  }
}
