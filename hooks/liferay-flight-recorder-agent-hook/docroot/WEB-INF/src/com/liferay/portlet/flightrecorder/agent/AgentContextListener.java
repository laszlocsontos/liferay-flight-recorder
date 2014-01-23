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

import com.liferay.portal.kernel.util.BasePortalLifecycle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author László Csontos
 */
public class AgentContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		_agent.init();
	}

	@Override
	protected void doPortalInit() throws Exception {
		_agent.destroy();
	}

	private Agent _agent = Agent.getInstance();

}