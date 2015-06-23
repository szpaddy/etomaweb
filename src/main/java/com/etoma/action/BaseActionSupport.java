package com.etoma.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseActionSupport extends ActionSupport
		implements
			SessionAware,
			RequestAware,
			ServletResponseAware,
			ServletRequestAware {
	private static Logger logger = Logger.getLogger(BaseActionSupport.class
			.getName());

	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected HttpServletResponse servletResponse;
	protected HttpServletRequest servletRequest;
	protected String basePath;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.servletResponse = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
		basePath = new StringBuffer(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).toString();
	}

	public String getBasePath() {
		return basePath;
	}

	public String getActionName() {
		return ActionContext.getContext().getActionInvocation().getProxy()
				.getActionName();
	}

	protected void transmitXML(String xmlStr) {
		transmitText(xmlStr, "application/xml");
	}

	protected void transmitPlainText(String plainText) {
		transmitText(plainText, "text/plain");
	}

	protected void transmitJson(String jsonStr) {
		transmitText(jsonStr, "application/json");
	}

	private void transmitText(String text, String contentType) {
		logger.debug("transmitText:\n" + text);
		servletResponse.setContentType(String.format("%s;charset=utf-8",
				contentType));
		servletResponse.setHeader("Cache-control", "no-cache");
		servletResponse.setHeader("pragma", "no-cache");

		PrintWriter out;
		try {
			out = servletResponse.getWriter();
			out.write(text);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.fatal("IOException when output ajax data", e);
		}
	}
}
