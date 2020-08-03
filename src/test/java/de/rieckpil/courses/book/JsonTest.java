package de.rieckpil.courses.book;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

  @Test
  void testWithJSONAssert() throws JSONException {
    String result = "{\"name\": \"duke\", \"age\":\"42\", \"hobbies\": [\"soccer\", \"java\"]}";
    JSONAssert.assertEquals("{\"name\": \"duke\"}", result, false);
    JSONAssert.assertEquals("{\"hobbies\": [\"java\", \"soccer\"]}", result, false);
  }

  @Test
  void testWithJsonPath() throws JSONException {

    // https://github.com/json-path/JsonPath

    String result = "{\"age\":\"42\", \"name\": \"duke\", \"tags\":[\"java\", \"jdk\"], \"orders\": [42, 42, 16]}";

    assertEquals(2, JsonPath.parse(result).read("$.tags.length()", Long.class));
    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
    assertEquals(100, JsonPath.parse(result).read("$.orders.sum()", Long.class));
  }
}
