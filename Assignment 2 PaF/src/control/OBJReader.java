package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.*;

public class OBJReader extends ReaderTemplate {

	public OBJReader(File directory) {
		super(".obj", directory);
	}

	@SuppressWarnings("resource")
	@Override
	public Component read() {
		Component masterComponent = null;
		ObjectInputStream ois = null;
		for(File f : super.getFiles()){
			if(getExtension(f).equals(getFileType())){
				try {
					ois = new ObjectInputStream(new FileInputStream(f));
					masterComponent = (Component)ois.readObject();
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("Couldn't read .obj file");
					e.printStackTrace();
				}
			}
		}		
		return masterComponent;
	}

}
