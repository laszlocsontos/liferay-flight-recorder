/**
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.flightrecorder.agent.model;

import java.lang.management.MemoryUsage;

/**
 * @author László Csontos
 */
public class HeapMemoryUsage extends Metric<Long, MemoryUsage> {

	public HeapMemoryUsage(MemoryUsage wrappedValue) {
		super(MetricType.HEAP_MEMORY_USAGE, wrappedValue);
	}

	@Override
	protected Long extractValue(MemoryUsage wrappedValue) {
		long usedMemory = wrappedValue.getUsed();

		return usedMemory;
	}

}