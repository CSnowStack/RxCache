/*
 * Copyright 2015 Victor Albertos
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

package io.rx_cache2.internal;

import io.rx_cache2.Source;

/**
 * Wrapper around the actual data in order to know if its life time has been expired
 *
 */
public final class Record {
  private Source source;
  private final String data;
  private final long timeAtWhichWasPersisted;
  private Boolean expirable;

  //LifeTime requires to be stored to be evicted by EvictExpiredRecordsTask when no life time is available without a config provider
  private Long lifeTime;

  //Required by EvictExpirableRecordsPersistence task
  private transient float sizeOnMb;

  //VisibleForTesting
  Record(String data) {
    this(data, true, null);
  }

  public Record() {
    data = null;
    timeAtWhichWasPersisted = 0;
    expirable = true;
  }

  public Record(String data, Boolean expirable, Long lifeTime) {
    this.data = data;
    this.expirable = expirable;
    this.lifeTime = lifeTime;
    this.timeAtWhichWasPersisted = System.currentTimeMillis();
    this.source = Source.MEMORY;


  }

  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }

  public String getData() {
    return data;
  }

  public long getTimeAtWhichWasPersisted() {
    return timeAtWhichWasPersisted;
  }

  public Long getLifeTime() {
    return lifeTime;
  }

  public void setLifeTime(Long lifeTime) {
    this.lifeTime = lifeTime;
  }

  public float getSizeOnMb() {
    return sizeOnMb;
  }

  public void setSizeOnMb(float sizeOnMb) {
    this.sizeOnMb = sizeOnMb;
  }


  public Boolean getExpirable() {
    return expirable;
  }

  public void setExpirable(Boolean expirable) {
    this.expirable = expirable;
  }
}
