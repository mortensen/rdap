/**
 * Copyright 2014 DNS Belgium vzw
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.dnsbelgium.rdap.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public abstract class AbstractSerializerTest<T extends JsonSerializer, S> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public abstract T getSerializer();

  @SuppressWarnings("unchecked")
  public void serializeAndAssertEquals(String expected, S o, SerializerProvider sp) throws IOException {
    JsonFactory factory = new JsonFactory();
    T serializer = getSerializer();
    StringWriter sw = new StringWriter();
    JsonGenerator jgen = factory.createGenerator(sw);
    serializer.serialize(o, jgen, sp); // unchecked
    jgen.flush();
    sw.flush();
    String result = sw.toString();
    sw.close();
    assertEquals(expected, result);
  }

  public void serializeAndAssertEquals(String expected, S o) throws IOException {
    serializeAndAssertEquals(expected, o, null);
  }


}
