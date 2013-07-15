package be.dns.rdap.jackson;

/*
 * #%L
 * Core
 * %%
 * Copyright (C) 2013 DNS Belgium vzw
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import be.dns.rdap.core.Entity;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;

public class RoleDeserializer extends JsonDeserializer<Entity.Role> {
  @Override
  public Entity.Role deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    final String value = jp.getText();
    //LOGGER.debug("Creating Role of value '{}'", value);
    try {
      return Entity.Role.Default.valueOf(value.toUpperCase(Locale.ENGLISH));
    } catch (IllegalArgumentException iae) {
      return new Entity.Role() {
        @Override
        public String getValue() {
          return value;
        }
      };
    }
  }
}
