
package be.dns.rdap;

/*
 * #%L
 * Server
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

import be.dns.core.CIDR;
import be.dns.rdap.core.IPNetwork;
import be.dns.rdap.service.IPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ip")
public final class IPController {

  @Autowired
  private IPService ipService;

  @RequestMapping(value = "/{ipaddress}", method = RequestMethod.GET, produces = Controllers.CONTENT_TYPE)
  @ResponseBody
  public IPNetwork get(@PathVariable("ipaddress") String ipAddress) {
    return ipService.getIPNetwork(CIDR.of(ipAddress));
  }

  @RequestMapping(value = "/{ipaddress}/{size}", method = RequestMethod.GET, produces = Controllers.CONTENT_TYPE)
  @ResponseBody
  public IPNetwork get(@PathVariable("ipaddress") String ipAddress, @PathVariable("size") int size) {
    return ipService.getIPNetwork(CIDR.of(ipAddress+"/"+size));
  }

}
