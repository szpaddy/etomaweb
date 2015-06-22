package com.etoma.core.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ZxingUtil {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final int WIDTH = 400; // 二维码宽度
	private static final int HEIGTH = 400; // 二维码高度

	/**
	 * 在给定的位置生成表示message的二维码
	 * 
	 * @param filePath
	 * @param message
	 * @return 生成的二维码文件路径；生成二维码异常时返回null
	 */
	public static String getQRCodePicFileName(String filePath, String message) {
		String fileName = String.format("qrcode_%s.jpg", UUID.randomUUID());
		try {
			BitMatrix matrix = getBitMatrix(message);
			writeToFile(matrix, "jpg", new File(filePath, fileName));
		} catch (Exception e) {
			fileName = null;
			e.printStackTrace();
		}

		return fileName;
	}

	/**
	 * 生成表示message的二维码图片
	 * 
	 * @param message
	 * @return 生成的BufferedImage；生成二维码异常时返回null
	 */
	public static BufferedImage getQRCodeImage(String message) {
		BufferedImage image = null;
		try {
			BitMatrix matrix = getBitMatrix(message);
			image = toBufferedImage(matrix);
		} catch (WriterException e) {
			e.printStackTrace();
		}

		return image;
	}

	@SuppressWarnings("unchecked")
	public static BitMatrix getBitMatrix(String message) throws WriterException {
		// 设置二维码编码
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);

		// 利用zxing编码得到二维码矩阵信息
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		return multiFormatWriter.encode(message, BarcodeFormat.QR_CODE, WIDTH,
				HEIGTH, hints);
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format,
			OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}
}
