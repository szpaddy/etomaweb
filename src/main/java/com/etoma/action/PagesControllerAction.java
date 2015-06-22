package com.etoma.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller()
@Namespace("/pages")
@Scope("prototype")
public class PagesControllerAction extends ActionSupport {

	@Action(value = "login", results = {@Result(name = "login", location = "login.html")})
	public String login() {
		return LOGIN;
	}

}
