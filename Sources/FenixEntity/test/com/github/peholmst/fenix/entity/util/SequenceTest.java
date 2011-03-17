/*
 * Copyright (c) 2011 Petter Holmström
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
package com.github.peholmst.fenix.entity.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.peholmst.fenix.common.util.Interval;

/**
 * Test case for {@link Sequence}.
 * 
 * @author Petter Holmström
 */
public class SequenceTest {

	static class SequenceUnderTest extends Sequence {

		static final long INCREMENT = 50;
		
		long sequenceValue = 1L;
		
		@Override
		protected Interval<Long> reserveSequenceValues() {
			 Interval<Long> interval = Interval.createClosedInterval(sequenceValue, sequenceValue + INCREMENT -1);
			 sequenceValue += INCREMENT;
			 return interval;
		}		
	}
	
	@Test
	public void initialReservationByConstructor() {
		SequenceUnderTest seq = new SequenceUnderTest();
		assertEquals(1L, seq.getNextValue());
		assertEquals(51L, seq.sequenceValue);
	}

	@Test
	public void loopThroughNextValuesUntilRangeRunsOut() {
		SequenceUnderTest seq = new SequenceUnderTest();
		long oldValue = 0L;
		for (int i = 0; i < SequenceUnderTest.INCREMENT; ++i) {
			long newValue = seq.getNextValue();
			assertEquals(oldValue +1, newValue);
			oldValue = newValue;
		}
		seq.sequenceValue = 200L;
		assertEquals(200L, seq.getNextValue());
		assertEquals(250L, seq.sequenceValue);
	}

}
