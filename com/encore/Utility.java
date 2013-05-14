package com.encore;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utility {

	public static String fileGetContent(String fileName) throws IOException {
		File file = new File(fileName);
		FileReader reader = new FileReader(file);
		StringBuffer buffer = new StringBuffer();
		int c;
		while ((c = reader.read()) != -1) {
			buffer.append((char) c);
		}
		String content = buffer.toString();
		reader.close();
		return content;
	}
}
