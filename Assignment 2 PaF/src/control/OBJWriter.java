package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.*;

public class OBJWriter extends WriterTemplate {

	public OBJWriter(File repositoryName) {
		super(".obj", repositoryName);
	}

	@Override
	public void write(Component masterComponent) {
		FileOutputStream fos =null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(super.getRepository().getName() + masterComponent.getName() + super.getFileType());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(masterComponent);		
			oos.close();
		} catch (IOException e) {
			System.out.println("Couldn't write .obj file");
			e.printStackTrace();
		}
		
	}

}
