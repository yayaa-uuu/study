/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.wx.chapter8.account;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import com.wx.chapter8.configurations.Configuration;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a test-case for the DefualtAccountManager class.
 * We use here the EasyMock library to mock the logger and the
 * configuration classes.
 */
public class TestDefaultAccountManagerEasyMock {
    private Log logger;

    private Configuration configuration;

    @BeforeEach
    public void setUp() {
        logger = createMock("logger", Log.class);
        configuration = createMock("configuration", Configuration.class);
    }

    @Test
    public void testFindAccountByUser() {
        expect(configuration.getSQL("FIND_ACCOUNT_FOR_USER")).andReturn("SELECT ..");
        replay();

        DefaultAccountManager2 am = new DefaultAccountManager2(logger, configuration);

        @SuppressWarnings("unused")
        Account account = am.findAccountForUser("1234");

        // Perform asserts here
    }
}
