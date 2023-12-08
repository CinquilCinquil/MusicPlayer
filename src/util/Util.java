package util;

import java.io.File;

public class Util {
	
	public static String getExtension(File file) {
		String fileName = file.toString();

	    int index = fileName.lastIndexOf('.');
	    if(index > 0) {
	      String extension = fileName.substring(index + 1);
	      return extension;
	    }
	    
	    return null;
	}

}
