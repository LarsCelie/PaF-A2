package control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.*;

public abstract class ReaderTemplate {
	protected File repository;
	private String fileType;
	
	public ReaderTemplate(String fileType, File directory){
		this.fileType = fileType;
		this.repository = directory;
	}
	
	public abstract Component read() throws IOException;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getExtension(File f) {
	    String ext = null;
	    String s = f.getPath();
	    int i = s.lastIndexOf('.');

	    if (i > 0 &&  i < s.length() - 1) {
	        ext = s.substring(i).toLowerCase();
	    }
	    return ext;
	}
	
	public ArrayList<File> getFiles(){
		ArrayList<File> files = new ArrayList<File>();
		File[] listOfFiles = repository.listFiles();
		for(File file : listOfFiles){
			if(file.isFile()){				
				if(this.getExtension(file).equals(this.getFileType())){
					files.add(file);
				}
			}
		}		
		return files;
	}
}
