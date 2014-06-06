/**
 * Copyright (C) 2012 JBoss Inc
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
package org.dashbuilder.dataset.function;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class AggregateFunctionManagerImpl implements AggregateFunctionManager {

    /**
     * The built-in aggregate function registry.
     */
    protected Map<String,AggregateFunction> functionMap = new HashMap<String,AggregateFunction>();

    @Inject
    protected Instance<AggregateFunction> _functionInstances;

    @PostConstruct
    protected void init() {
        for (AggregateFunction sf: _functionInstances) {
            functionMap.put(sf.getCode(), sf);
        }
    }

    public Collection<AggregateFunction> getAllFunctions() {
        return functionMap.values();
    }

    public AggregateFunction getFunctionByCode(String code) {
        return functionMap.get(code);
    }

    public void registerFunction(String code, AggregateFunction function) {
        functionMap.put(code, function);
    }
}