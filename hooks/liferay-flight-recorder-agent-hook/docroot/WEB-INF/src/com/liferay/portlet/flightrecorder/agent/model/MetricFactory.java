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
public final class MetricFactory {

	public static Metric<?, ?> createHeapMemoryUsage(MemoryUsage value) {
		return new HeapMemoryUsage(value);
	}

	public static Metric<?, ?> createNonHeapMemoryUsage(MemoryUsage value) {
		return new NonHeapMemoryUsage(value);
	}

	public static Metric<?, ?> createSystemLoadAverage(double value) {
		return new SystemLoadAverage(value);
	}

	private MetricFactory() {
	}

}