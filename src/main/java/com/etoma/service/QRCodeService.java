package com.etoma.service;

public interface QRCodeService {

	/**
	 * 将给定的内容转换成二维码
	 * 
	 * @param message
	 * @return 二维码路径; 生成二维码异常时返回null
	 */
	public String genQRCode(String filePath, String message);
}
