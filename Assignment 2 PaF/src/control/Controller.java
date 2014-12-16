package control;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.Component;
import model.Composite;
import model.Pattern;

public class Controller {
	private static Controller instance;
	private ImportExportController importExportController;
	private Component masterComponent;
	
	/**
	 * the constructor, initialize the importExportController and master Component
	 */
	public Controller(){
		masterComponent = new Composite("master");
		this.setImportExport(ImportExportController.getInstance());
		this.setMasterComponent(getImportExport().importRepository());
	}
	
	/**
	 * Returns an instance of controller if it exists otherwise creates a new instance
	 * @return the instance of controller if it exists
	 */
	public static Controller getInstance(){
		if(instance == null) {
			instance = new Controller();
		}
		return instance;		
	}

	/**
	 * 
	 * @return the importExportController
	 */
	public ImportExportController getImportExport() {
		return importExportController;
	}
	/**
	 * 
	 * @param importExport the ImportExportController
	 */
	public void setImportExport(ImportExportController importExport) {
		this.importExportController = importExport;
	}

	/**
	 * @return the masterComponent
	 */
	public Component getMasterComponent() {
		return masterComponent;
	}

	/**
	 * @param masterComponent the masterComponent to set
	 */
	public void setMasterComponent(Component masterComponent) {
		this.masterComponent = masterComponent;
	}

	public Object[][] getTableData(String scopeName, String purposeName){
		ArrayList<Component> patterns = getPatterns(scopeName, purposeName);
		Object[][] tableData = new Object[patterns.size()][3];		
		int i = 0;
		for(Component c : patterns){
			Pattern p = (Pattern) c;
			Object[] row = {p.getName(), p.getProblems(), p.getConsequences()};
			tableData[i] = row;
			i++;
		}
		return tableData;
	}	
	
	public ArrayList<Component> getPatterns(String scopeName, String purposeName){
		ArrayList<Component> scopes = new ArrayList<Component>();
		ArrayList<Component> purposes = new ArrayList<Component>();
		ArrayList<Component> patterns = new ArrayList<Component>();
		if(scopeName == null || scopeName.equals("Any")){
			scopes =  masterComponent.getChilds();
		}else
			scopes.add(masterComponent.getChild(scopeName));
		for(Component scopeC : scopes){
			if(purposeName == null || purposeName.equals("Any")){
				for(Component purposeC : scopeC.getChilds()){
					purposes.add(purposeC);
				}
			}else
				purposes.add(scopeC.getChild(purposeName));
		}
		for(Component p : purposes){
			for(Component pattern : p.getChilds()){
				patterns.add(pattern);
			}
		}
		return patterns;
	}
	
	public Pattern getPattern(String patternName){
		Pattern returnPattern = null;
		for(Component p : getPatterns("Any", "Any")){
			if(p.getName().equals(patternName)){
				if(p instanceof Pattern)
					returnPattern = (Pattern) p;
			}
		}
		return returnPattern;
	}
	
	public ArrayList<Pattern> getPatterns(ArrayList<String> patternNames){
		ArrayList<Pattern> patterns = new ArrayList<Pattern>();
		for(String s : patternNames){
			patterns.add(getPattern(s));
		}
		return patterns;
	}

	public boolean makePattern(String scope, String purpose, String name, ArrayList<String> problems, ArrayList<String> consequences, String solution, ImageIcon diagram, ArrayList<String> relatedPatternNames) {
		//Check if already exists
		if(getPattern(name) != null)
			return false;		
		// else make Pattern and add to hierarchie
		Component newPattern = new Pattern(name, problems, consequences, solution, diagram, getPatterns(relatedPatternNames));
		masterComponent.getChild(scope).getChild(purpose).addComponent(newPattern);
		
		return true;
	}
	
	public void editPattern(Pattern pat, String name, ArrayList<String> problems, ArrayList<String> consequences, String solution, ImageIcon diagram){
		pat.setName(name);
		pat.setProblems(problems);
		pat.setConsequences(consequences);
		pat.setSolution(solution);
		pat.setDiagram(diagram);
	}
}
