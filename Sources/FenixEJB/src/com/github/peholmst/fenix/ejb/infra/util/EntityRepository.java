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
package com.github.peholmst.fenix.ejb.infra.util;

import java.util.List;

import com.github.peholmst.fenix.entity.util.EntityBase;
import com.github.peholmst.fenix.entity.util.OrderInstruction;

/**
 * 
 * @author peholmst
 * 
 * @param <E>
 */
public interface EntityRepository<E extends EntityBase> {

    /**
     * 
     * @param entity
     * @return
     */
    E save(E entity);

    /**
     * 
     * @param entity
     */
    void delete(E entity);

    /**
     * 
     * @param identifier
     * @return
     */
    E findByIdentifier(Long identifier);

    /**
     * 
     * @param returnMax
     * @param orderBy
     * @return
     */
    List<E> findAll(int returnMax, OrderInstruction<E>... orderBy);
}
