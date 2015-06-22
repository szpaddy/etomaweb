package com.etoma.core.service;

import org.junit.Assert;
import org.junit.Test;

import com.etoma.service.QRCodeService;
import com.etoma.service.impl.QRCodeServiceImpl;

public class QRCodeServiceTest {

	@Test
	public void genQRCode() {
		QRCodeService service = new QRCodeServiceImpl();
		String picPath = service.genQRCode("d:/",
				"http://home.baidu.com/2015-05-07/1437879812.html");

		Assert.assertNotNull(picPath);
	}

}
