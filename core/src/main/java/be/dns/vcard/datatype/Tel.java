package be.dns.vcard.datatype;

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

import be.dns.core.TelephoneNumber;

import java.net.URI;
import java.net.URISyntaxException;

public class Tel extends URIValue {

  public Tel(TelephoneNumber telephoneNumber) throws URISyntaxException {
    super(new URI("tel:" + telephoneNumber.getStringValue()));
  }

  public Tel(String telephoneNumber) throws URISyntaxException {
    this(TelephoneNumber.of(telephoneNumber));
  }
}