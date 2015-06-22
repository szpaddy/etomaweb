package com.etoma.core.qrcode.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etoma.core.qrcode.ZxingUtil;
import com.google.zxing.WriterException;

@SuppressWarnings("serial")
public class QrcodeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置响应头 Content-type类型
		resp.setContentType("image/jpeg");
		// 以下三句是用于设置页面不缓存
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "No-cache");
		resp.setDateHeader("Expires", 0);

		OutputStream os = resp.getOutputStream();
		String message = req.getParameter("message");

		// 输出图像到页面
		try {
			ZxingUtil.writeToStream(ZxingUtil.getBitMatrix(message), "jpg", os);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
