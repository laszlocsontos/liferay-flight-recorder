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

package com.liferay.portlet.flightrecorder.agent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author László Csontos
 */
public class Agent {

	public static Agent getInstance() {
		if (_instance == null) {
			_instance = new Agent();
		}

		return _instance;
	}

	public void destroy() {
		if (_log.isInfoEnabled()) {
			_log.info("Agent initialized.");
		}
	}

	public void init() {
		if (_log.isInfoEnabled()) {
			_log.info("Agent destroyed.");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(Agent.class);

	private static Agent _instance;

}