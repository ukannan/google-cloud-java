/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gcloud.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import com.google.gcloud.storage.spi.StorageRpc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OptionTest {

  private static final StorageRpc.Option RPC_OPTION = StorageRpc.Option.DELIMITER;
  private static final StorageRpc.Option ANOTHER_RPC_OPTION = StorageRpc.Option.FIELDS;
  private static final String VALUE = "some value";
  private static final String OTHER_VALUE = "another value";
  private static final Option OPTION = new Option(RPC_OPTION, VALUE) {};
  private static final Option OPTION_EQUALS = new Option(RPC_OPTION, VALUE) {};
  private static final Option OPTION_NOT_EQUALS1 = new Option(RPC_OPTION, OTHER_VALUE) {};
  private static final Option OPTION_NOT_EQUALS2 = new Option(ANOTHER_RPC_OPTION, VALUE) {};

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testEquals() {
    assertEquals(OPTION, OPTION_EQUALS);
    assertNotEquals(OPTION, OPTION_NOT_EQUALS1);
    assertNotEquals(OPTION, OPTION_NOT_EQUALS2);
  }

  @Test
  public void testHashCode() {
    assertEquals(OPTION.hashCode(), OPTION_EQUALS.hashCode());
  }

  @Test
  public void testConstructor() {
    assertEquals(RPC_OPTION, OPTION.rpcOption());
    assertEquals(VALUE, OPTION.value());
    Option option = new Option(RPC_OPTION, null) {};
    assertEquals(RPC_OPTION, option.rpcOption());
    assertNull(option.value());
    thrown.expect(NullPointerException.class);
    new Option(null, VALUE) {};
  }
}
