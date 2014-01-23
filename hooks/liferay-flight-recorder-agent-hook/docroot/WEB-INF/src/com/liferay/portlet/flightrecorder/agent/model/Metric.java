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

import java.io.Serializable;

/**
 * @author László Csontos
 */
public abstract class Metric<V extends Number, W> implements Serializable {

	public Metric() {
	}

	public Metric(MetricType type, W wrappedValue) {
		setType(type);
		setWrappedValue(wrappedValue);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public MetricType getType() {
		return type;
	}

	public V getValue() {
		if ((value == null) && (wrappedValue != null)) {
			value = extractValue(wrappedValue);
		}

		return value;
	}

	public Object getWrappedValue() {
		return wrappedValue;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setType(MetricType type) {
		this.type = type;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public void setWrappedValue(W wrappedValue) {
		this.wrappedValue = wrappedValue;

		value = extractValue(wrappedValue);
	}

	protected abstract V extractValue(W wrappedValue);

	protected long timestamp;
	protected MetricType type;
	protected V value;
	protected transient W wrappedValue;

}