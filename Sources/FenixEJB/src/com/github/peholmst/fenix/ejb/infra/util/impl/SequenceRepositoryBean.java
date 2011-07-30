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
package com.github.peholmst.fenix.ejb.infra.util.impl;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import com.github.peholmst.fenix.ejb.infra.util.SequenceRepository;
import com.github.peholmst.fenix.entity.util.Sequence;
import com.github.peholmst.fenix.entity.util.SequenceType;

/**
 * TODO Document me!
 * 
 * @author peholmst
 * 
 */
@Singleton
public class SequenceRepositoryBean implements SequenceRepository {

    // TODO Use EJB locking or JPA locking?

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long getNextValue(SequenceType type) {
        Sequence seq = entityManager.find(Sequence.class, type,
                LockModeType.PESSIMISTIC_WRITE);
        if (seq == null) {
            seq = new Sequence();
            seq.setSequenceType(type);
            entityManager.persist(seq);
        }
        long value = seq.incrementAndReturnSequenceValue();
        entityManager.flush();
        return value;
    }

}
