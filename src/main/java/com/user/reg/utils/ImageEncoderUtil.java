package com.user.reg.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class ImageEncoderUtil {
 	
	public static byte[] getImageByteArray(String encode64) throws IOException {
		BufferedImage image = getImageFromEncode64(encode64);
		ByteArrayOutputStream byteArray = 
				new ByteArrayOutputStream();
		ImageIO.write(image, "png", byteArray);
		
		return byteArray.toByteArray();
	}
	
	private static BufferedImage getImageFromEncode64(String encode64) {
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(
				encode64);
		
		BufferedImage bufferedImage = null;
		
		try {
			bufferedImage = ImageIO.read(
					new ByteArrayInputStream(imageBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bufferedImage; 
	}
	
}
