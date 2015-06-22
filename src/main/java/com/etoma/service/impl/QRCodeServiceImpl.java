package com.etoma.service.impl;

import com.etoma.core.qrcode.ZxingUtil;
import com.etoma.service.QRCodeService;

public class QRCodeServiceImpl implements QRCodeService {

	@Override
	public String genQRCode(String filePath, String message) {
		return ZxingUtil.getQRCodePicFileName(filePath, message);
	}

}
