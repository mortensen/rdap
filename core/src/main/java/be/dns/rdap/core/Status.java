package be.dns.rdap.core;

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

import java.util.Locale;

/**
 * Not an enum since it should be extensible
 */
public interface Status {


  public static enum Default implements Status {
    VALIDATED("validated"),
    UPDATE_PROHIBITED("update prohibited"),
    TRANSFER_PROHIBITED("transfer prohibited"),
    DELETE_PROHIBITED("delete prohibited"),
    PROXY("proxy"),
    PRIVATE("private"),
    REDACTED("redacted"),
    OBSCURED("obscured"),
    ASSOCIATED("associated");

    private final String value;

    Default(String value) {
      this.value = value;
    }

    @Override
    public String getValue() {
      return this.value;
    }

  }

  String getValue();

  public static class BasicStatus implements Status {

    private final String value;

    public BasicStatus(String value) {
      this.value = value;
    }

    @Override
    public String getValue() {
      return value;
    }

  }

  public abstract static class Factory {

    public static Status of(String status) {
      try {
        return Default.valueOf(status.toUpperCase(Locale.ENGLISH));
      } catch (IllegalArgumentException iae) {
        return new BasicStatus(status);
      }
    }
  }


}
