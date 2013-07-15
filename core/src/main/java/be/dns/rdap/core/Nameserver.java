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

import be.dns.core.DomainName;
import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Nameserver extends Common {


  public static class IpAddresses {

    private final List<String> v4;

    private final List<String> v6;

    @JsonCreator
    public IpAddresses(
        @JsonProperty("v4") List<String> v4,
        @JsonProperty("v6") List<String> v6) {
      this.v4 = new ImmutableList.Builder<String>().addAll(v4).build();
      this.v6 = new ImmutableList.Builder<String>().addAll(v6).build();
    }

    public IpAddresses(List<InetAddress> inetAddresses) {
      ImmutableList.Builder<String> v4Builder = new ImmutableList.Builder<String>();
      ImmutableList.Builder<String> v6Builder = new ImmutableList.Builder<String>();
      for (InetAddress addr : inetAddresses) {
        if (addr instanceof Inet4Address) v4Builder.add(addr.getHostAddress());
        if (addr instanceof Inet6Address) v6Builder.add(addr.getHostAddress());
      }
      v4 = v4Builder.build();
      v6 = v6Builder.build();
    }


    public List<String> getV4() {
      return v4;
    }

    public List<String> getV6() {
      return v6;
    }

  }

  private final String handle;

  private final DomainName ldhName;

  private final DomainName unicodeName;

  public IpAddresses getIpAddresses() {
    return ipAddresses;
  }

  public DomainName getUnicodeName() {
    return unicodeName;
  }

  public DomainName getLdhName() {
    return ldhName;
  }

  public String getHandle() {
    return handle;
  }

  private final IpAddresses ipAddresses;

  @JsonCreator
  public Nameserver(
      @JsonProperty("rdapConformance") Set<String> rdapConformance,
      @JsonProperty("links") List<Link> links,
      @JsonProperty("notices") List<Notice> notices,
      @JsonProperty("remarks") List<Notice> remarks,
      @JsonProperty("lang") String lang,
      @JsonProperty("events") List<Event> events,
      @JsonProperty("status") List<Status> status,
      @JsonProperty("port43") DomainName port43,
      @JsonProperty("handle") String handle,
      @JsonProperty("ldhName") DomainName ldhName,
      @JsonProperty("unicodeName") DomainName unicodeName,
      @JsonProperty("ipAddresses") IpAddresses ipAddresses) {
    super(rdapConformance, links, notices, remarks, lang, events, status, port43);
    this.handle = handle;
    this.ldhName = ldhName;
    this.unicodeName = unicodeName;
    this.ipAddresses = ipAddresses;
  }

  public static class Builder {

    private List<InetAddress> inetAddresses;

    private DomainName ldhName;

    private DomainName unicodeName;

    public Builder addInetAddress(InetAddress addr) {
      if (addr == null) return this;
      if (inetAddresses == null) inetAddresses = new ArrayList<InetAddress>();
      inetAddresses.add(addr);
      return this;
    }

    public Builder setLDHName(DomainName ldhName) {
      this.ldhName = ldhName;
      return this;
    }

    public Builder setUnicodeName(DomainName unicodeName) {
      this.unicodeName = unicodeName;
      return this;
    }

    public Nameserver build() {
      return new Nameserver(null, null, null, null, null, null, null, null, null, ldhName, unicodeName, new IpAddresses(inetAddresses));
    }
  }
}
