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
import com.google.common.collect.ImmutableSet;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * Thread safe
 */
class Common {

  public static final String DEFAULT_RDAP_CONFORMANCE = "rdap_level_0";

  private final Set<String> rdapConformance;

  private final List<Link> links;

  private final List<Notice> notices;

  private final List<Notice> remarks;

  private final String lang;

  private final List<Event> events;

  private final List<Status> status;

  private final DomainName port43;

  @JsonCreator
  public Common(
      @JsonProperty("rdapConformance") Set<String> rdapConformance,
      @JsonProperty("links") List<Link> links,
      @JsonProperty("notices") List<Notice> notices,
      @JsonProperty("remarks") List<Notice> remarks,
      @JsonProperty("lang") String lang,
      @JsonProperty("events") List<Event> events,
      @JsonProperty("status") List<Status> status,
      @JsonProperty("port43") DomainName port43
  ) {
    this.rdapConformance = rdapConformance == null ? new ImmutableSet.Builder<String>().add(DEFAULT_RDAP_CONFORMANCE).build() : rdapConformance;
    this.links = links == null ? null : new ImmutableList.Builder<Link>().addAll(links).build();
    this.notices = notices == null ? null : new ImmutableList.Builder<Notice>().addAll(notices).build();
    this.remarks = remarks == null ? null : new ImmutableList.Builder<Notice>().addAll(remarks).build();
    this.lang = lang;
    this.events = events == null ? null : new ImmutableList.Builder<Event>().addAll(events).build();
    this.status = status == null ? null : new ImmutableList.Builder<Status>().addAll(status).build();
    this.port43 = port43;
  }

  public Set<String> getRdapConformance() {
    return rdapConformance;
  }

  public List<Link> getLinks() {
    return links;
  }

  public List<Notice> getNotices() {
    return notices;
  }

  public List<Notice> getRemarks() {
    return remarks;
  }

  public String getLang() {
    return lang;
  }

  public List<Event> getEvents() {
    return events;
  }

  public List<Status> getStatus() {
    return status;
  }

  public DomainName getPort43() {
    return port43;
  }
}
