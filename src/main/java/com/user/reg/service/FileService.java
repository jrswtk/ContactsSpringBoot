package com.user.reg.service;

import java.awt.Image;
import java.io.FileNotFoundException;

public interface FileService {

	Image getImageFromPath(String path) throws FileNotFoundException;
	byte[] getImageByteArray(Image image);
	
}
