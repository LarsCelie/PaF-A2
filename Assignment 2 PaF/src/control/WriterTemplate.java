package control;

import java.io.File;

import model.*;

public abstract class WriterTemplate {
	private String fileType;
	private File repository;
	
	public WriterTemplate(String fileType, File repository){
		this.fileType = fileType;
		this.setRepository(repository);
	}
	
	public abstract void write(Component masterComponent);

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public File getRepository() {
		return repository;
	}

	public void setRepository(File repository) {
		this.repository = repository;
	}
	
}
