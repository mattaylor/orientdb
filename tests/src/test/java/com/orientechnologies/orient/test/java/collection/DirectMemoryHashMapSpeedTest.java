/*
 * Copyright 1999-2012 Luca Garulli (l.garulli--at--orientechnologies.com)
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

package com.orientechnologies.orient.test.java.collection;

import com.orientechnologies.common.directmemory.ODirectMemory;
import com.orientechnologies.common.directmemory.ODirectMemoryFactory;
import com.orientechnologies.common.directmemory.collections.ODirectMemoryHashMap;
import com.orientechnologies.common.serialization.types.OBinaryTypeSerializer;
import com.orientechnologies.common.serialization.types.OStringSerializer;
import com.orientechnologies.common.util.MersenneTwisterFast;

import org.testng.annotations.Test;

/**
 * @author Andrey Lomakin
 * @since 19.08.12
 */
public class DirectMemoryHashMapSpeedTest extends CollectionBaseAbstractSpeedTest {
  private static final int                     COUNT  = 1000000;

  private ODirectMemoryHashMap<String, byte[]> hashMap;
  private MersenneTwisterFast                  random = new MersenneTwisterFast();
  private ODirectMemory                        memory;

  public DirectMemoryHashMapSpeedTest() {
    super(COUNT);
  }

  @Override
  @Test(enabled = false)
  public void cycle() {
    hashMap.get(String.valueOf(random.nextInt()));
  }

  @Override
  @Test(enabled = false)
  public void init() throws Exception {
    memory = ODirectMemoryFactory.INSTANCE.directMemory();

    hashMap = new ODirectMemoryHashMap<String, byte[]>(memory, OBinaryTypeSerializer.INSTANCE, OStringSerializer.INSTANCE);

    while (hashMap.size() < COUNT)
      hashMap.put(String.valueOf(random.nextInt()), new byte[1024]);
  }
}
