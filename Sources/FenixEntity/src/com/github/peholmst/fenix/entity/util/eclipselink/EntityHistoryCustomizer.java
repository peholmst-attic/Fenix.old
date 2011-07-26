/*
 * Copyright (c) 2011 The original developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.entity.util.eclipselink;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;

/**
 * TODO Document me!
 * 
 * @see http://wiki.eclipse.org/EclipseLink/Examples/JPA/History
 * 
 * @author Petter Holmström
 */
public class EntityHistoryCustomizer implements DescriptorCustomizer {

    @Override
    public void customize(ClassDescriptor descriptor) throws Exception {
        final HistoryPolicy policy = new HistoryPolicy();
        policy.addHistoryTableName(descriptor.getTableName() + "_HISTORY");
        policy.addStartFieldName("HIST_START_DATE");
        policy.addEndFieldName("HIST_END_DATE");
        descriptor.setHistoryPolicy(policy);
    }

}
