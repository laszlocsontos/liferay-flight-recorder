package com.liferay.test;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Portlet implementation class SimplePortlet
 */
public class SimplePortlet extends GenericPortlet {

	public void getJMXInfo() {

		List memBeans = ManagementFactory.getMemoryPoolMXBeans();
		for (Iterator i = memBeans.iterator(); i.hasNext();) {

			MemoryPoolMXBean mpool = (MemoryPoolMXBean) i.next();
			MemoryUsage usage = mpool.getUsage();

			String name = mpool.getName();
			float init = usage.getInit() / 1000;
			float used = usage.getUsed() / 1000;
			float committed = usage.getCommitted() / 1000;
			float max = usage.getMax() / 1000;
			float pctUsed = (used / max) * 100;
			float pctCommitted = (committed / max) * 100;

			System.out.println(name);
			System.out.println(String.valueOf(init));
			System.out.println(String.valueOf(used));
			System.out.println(String.valueOf(committed));
			System.out.println(String.valueOf(max));
			System.out.println(String.valueOf(pctUsed));
			System.out.println(String.valueOf(pctCommitted));

		}

	}

	public void getThreads() {

		ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

		ThreadInfo[] threadInfos = mxBean.getThreadInfo(
				mxBean.getAllThreadIds(), 0);
		Map<Long, ThreadInfo> threadInfoMap = new HashMap<Long, ThreadInfo>();
		for (ThreadInfo threadInfo : threadInfos) {
			threadInfoMap.put(threadInfo.getThreadId(), threadInfo);
		}

		Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();

		for (Map.Entry<Thread, StackTraceElement[]> entry : stacks.entrySet()) {

			Thread thread = entry.getKey();

			System.out.println("\"" + thread.getName() + "\" prio="
					+ thread.getPriority() + " tid=" + thread.getId() + " "
					+ thread.getState() + " "
					+ (thread.isDaemon() ? "deamon" : "worker") + " ");

			ThreadInfo threadInfo = threadInfoMap.get(thread.getId());

			if (threadInfo != null) {

				System.out.println("    native=" + threadInfo.isInNative()
						+ ", suspended=" + threadInfo.isSuspended()
						+ ", block=" + threadInfo.getBlockedCount() + ", wait="
						+ threadInfo.getWaitedCount() + " ");

				System.out.println("    lock=" + threadInfo.getLockName()
						+ " owned by " + threadInfo.getLockOwnerName() + " ("
						+ threadInfo.getLockOwnerId() + "), cpu="
						+ mxBean.getThreadCpuTime(threadInfo.getThreadId())
						/ 1000000L + ", user="
						+ mxBean.getThreadUserTime(threadInfo.getThreadId())
						/ 1000000L + " ");

			}

			for (StackTraceElement element : entry.getValue()) {

				System.out.println("        ");
				System.out.println(element.toString());
				System.out.println("\n");

			}

			System.out.println("\n");

		}

	}

	public void init() {
		editJSP = getInitParameter("edit-template");
		viewJSP = getInitParameter("view-template");
	}

	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

		getThreads();

	}

	public void doEdit(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		include(editJSP, renderRequest, renderResponse);
	}

	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		include(viewJSP, renderRequest, renderResponse);
	}

	protected void include(String path, RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher = getPortletContext()
				.getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		} else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}

	protected String editJSP;
	protected String viewJSP;

	private static Log _log = LogFactoryUtil.getLog(SimplePortlet.class);

}
