package com.etoma.action;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.etoma.core.util.HttpUtil;

@SuppressWarnings("serial")
@Controller()
@Namespace("/pages")
@Scope("prototype")
public class PagesControllerAction extends BaseActionSupport {

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String username;
	private String password;

	@Action(value = "login", results = {@Result(name = "login", location = "login.html")})
	public String login() {
		return LOGIN;
	}

	@Action(value = "order", results = {
			@Result(name = "success", location = "index.html", type = "redirect"),
			@Result(name = "login", location = "login.html", type = "redirect")})
	public String order() {
		String username = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("username");
		if (username != null) {
			// 若已登录跳转到订单页面
			return SUCCESS;
		} else {
			// 若尚未登录跳转到登录页面
			return LOGIN;
		}
	}

	@Action(value = "accessCheck")
	public void accessCheck() {
		String registerUrl = String.format(
				"www.etoma.cn/api/login/submit.jhtml?username=%s&password=%s",
				username, password);
		String response = HttpUtil.doGetRequest(registerUrl);
		JSONObject jsonObj = JSONObject.fromObject(response);

		String msg = "登录失败";
		if (jsonObj.getString("code").equals("0")) {
			ServletActionContext.getRequest().getSession()
					.setAttribute("username", username);
			msg = "登录成功";
		}

		this.transmitJson(String.format("{\"msg\":\"%s\"}", msg));
	}

	@Action(value = "register")
	public void register() {
		String registerUrl = String
				.format("http://www.etoma.cn/api/register/submit.jhtml?a=%s&b=%s&c=%s&d=%s&e=%s&f=%s",
						a, b, c, d, e, f);
		String response = HttpUtil.doGetRequest(registerUrl);
		JSONObject jsonObj = JSONObject.fromObject(response);

		String msg = (jsonObj.getString("code").equals("0")) ? "注册成功" : "注册失败";
		this.transmitJson(String.format("{\"msg\":\"%s\"}", msg));
	}

	public void setA(String a) {
		this.a = a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public void setC(String c) {
		this.c = c;
	}

	public void setD(String d) {
		this.d = d;
	}

	public void setE(String e) {
		this.e = e;
	}

	public void setF(String f) {
		this.f = f;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
