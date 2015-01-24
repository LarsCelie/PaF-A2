package control;

import java.io.File;
import java.io.IOException;

import model.*;

public class ImportExportController {
	private File repository = new File(new File(".").getAbsolutePath());
	private static ImportExportController instance;
	private ReaderTemplate defaultReader;
	private WriterTemplate defaultWriter;

	protected ImportExportController() {
		this.setDefaultReader(new OBJReader(repository));
		this.setDefaultWriter(new OBJWriter(repository));
	}

	/**
	 * Returns an instance of importExport if it exists otherwise creates a new
	 * instance
	 * 
	 * @return the instance of importExport if it exists
	 */
	public static ImportExportController getInstance() {
		if (instance == null) {
			instance = new ImportExportController();
		}
		return instance;
	}
	
	/**
	 * import method that imports the MasterComponent with the defaultReader
	 * @return the masterComponent with all the childs that are imported by the defaultReader
	 */
	public Component importRepository(){
		System.out.println("trying to import patterns");
		//System.out.println(repository);
		Component c = new Composite("master");
		try{
			c = defaultReader.read();
		} catch(IOException ioe){
			System.out.println("Repository doesn't exists");
			Component scope1 = new Composite("Object scope");
			Component scope2 = new Composite("Class scope");
			
			scope1.addComponent(new Composite("Creational purpose"));
			scope1.addComponent(new Composite("Structural purpose"));
			scope1.addComponent(new Composite("Behavioral purpose"));
			
			scope2.addComponent(new Composite("Creational purpose"));
			scope2.addComponent(new Composite("Structural purpose"));
			scope2.addComponent(new Composite("Behavioral purpose"));
			
			c.addComponent(scope1);
			c.addComponent(scope2);
		}		
		return c;
	}
	/**
	 * export method that exports the MasterComponent with the defaultWriter
	 * @param masterComponent the master with all childs that needs to exported
	 */
	public void exportRepository(Component masterComponent){
		System.out.println("trying to export patterns");
		defaultWriter.write(masterComponent);
	}
	
	/**
	 * getter for the import and export directory
	 * @return the directory
	 */
	public File getDirectory() {
		return repository;
	}

	/**
	 * setter for the import and export directory
	 * @param directory the directory where to import and export
	 */
	public void setDirectory(File directory) {
		this.repository = directory;
	}

	/**
	 * getter for default writer
	 * @return the defaultwriter
	 */
	public WriterTemplate getDefaultWriter() {
		return defaultWriter;
	}
	
	/**
	 * setter for the default writer
	 * @param defaultWriter the defaultWriter to set
	 */
	public void setDefaultWriter(WriterTemplate defaultWriter) {
		this.defaultWriter = defaultWriter;
	}

	/**
	 * getter for default reader
	 * @return the defaultReader
	 */
	public ReaderTemplate getDefaultReader() {
		return defaultReader;
	}

	/**
	 * setter for the default reader
	 * @param defaultReader the defaultReader to set
	 */
	public void setDefaultReader(ReaderTemplate defaultReader) {
		this.defaultReader = defaultReader;
	}
}
