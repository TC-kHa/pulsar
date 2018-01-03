/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pulsar.functions.runtime.worker;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.distributedlog.api.namespace.Namespace;
import org.apache.pulsar.functions.runtime.container.ThreadFunctionContainerFactory;
import org.apache.pulsar.functions.runtime.spawner.LimitsConfig;
import org.apache.pulsar.functions.runtime.worker.request.ServiceRequestManager;
import org.junit.Test;

public class FunctionMetaDataManagerTest {

    @Test
    public void testClose() {
        try {
            WorkerConfig config = new WorkerConfig();
            ServiceRequestManager reqMgr = mock(ServiceRequestManager.class);
            FunctionMetaDataManager fsm = new FunctionMetaDataManager(config,
                    new LimitsConfig(-1, -1, -1, -1), reqMgr,
                    new ThreadFunctionContainerFactory(-1, null, null),
                    mock(Namespace.class));
            fsm.close();
            verify(reqMgr, times(1)).close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
