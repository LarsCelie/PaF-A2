package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pattern implements Component, java.io.Serializable {
	private static final long serialVersionUID = 3890072520591433950L;
	private String name;
	private ArrayList<String> problems;
	private ArrayList<String> consequences;
	private String solution;
	private ArrayList<Pattern> relatedPatterns;
	private ImageIcon icon;
	
	/**
	 * The constructor
	 * 
	 * @param String the name
	 * @param ArrayList<String> the problems
	 * @param ArrayList<String> the consequences
	 * @param String the solution
	 * @param BufferedImage the diagram
	 * @param ArrayList<Patterns> the relatedpatterns
	 */
	public Pattern(String name, ArrayList<String> problems, ArrayList<String> consequences, String solution, ImageIcon diagram, ArrayList<Pattern> relatedPatterns){
		this.name = name;
		this.setProblems(problems);
		this.setConsequences(consequences);
		this.setSolution(solution);
		this.setDiagram(diagram);
		this.setRelatedPatterns(relatedPatterns);
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * not needed, because end of hierarchy
	 */
	@Override
	public void addComponent(Component c) {	}
	
	/**
	 * not needed, because end of hierarchy
	 */
	@Override
	public void removeComponent(Component c) {	}
	
	/**
	 * not needed, because end of hierarchy
	 */
	@Override
	public ArrayList<Component> getChilds() { return null; }
	
	/**
	 * 
	 * @return Component the component if the names are the same otherwise NULL
	 */
	@Override
	public Component getChild(String name) {
		Component c = null;
		if(name.equals(getName())){
			c = this;
		}
		return c;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the problems
	 */
	public ArrayList<String> getProblems() {
		return problems;
	}

	/**
	 * @param problems the problems to set
	 */
	public void setProblems(ArrayList<String> problems) {
		this.problems = problems;
	}

	/**
	 * @return the consequences
	 */
	public ArrayList<String> getConsequences() {
		return consequences;
	}

	/**
	 * @param consequences the consequences to set
	 */
	public void setConsequences(ArrayList<String> consequences) {
		this.consequences = consequences;
	}

	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 * @return the diagram
	 */
	public ImageIcon getDiagram() {
		return icon;
	}

	/**
	 * @param diagram the diagram to set
	 */
	public void setDiagram(ImageIcon diagram) {
		this.icon = diagram;
	}

	/**
	 * @return the relatedPatterns
	 */
	public ArrayList<Pattern> getRelatedPatterns() {
		return relatedPatterns;
	}

	/**
	 * @param relatedPatterns the relatedPatterns to set
	 */
	public void setRelatedPatterns(ArrayList<Pattern> relatedPatterns) {
		this.relatedPatterns = relatedPatterns;
	}
	
	@Override
	public boolean equals(Object object){
		boolean isEqual = false;
		if(object != null && object instanceof Pattern){
			Pattern p = (Pattern) object;
			isEqual = this.getName().equals(p.getName());
		}
		return isEqual;		
	}
	
	/**
	 * 
	 * @return String a single String that is composed of multiple Strings, seperated by a newline
	 */
	public String getStringProblems(){
		String text = "";
		for (String s : getProblems()){
			text += s + "\n";
		}
		return text;
	}
	
	/**
	 * 
	 * @return String a single String that is composed of multiple Strings, seperated by a newline
	 */
	public String getStringConsequences(){
		String text = "";
		for (String s : getConsequences()){
			text += s + "\n";
		}
		return text;
	}
	
	/**
	 * 
	 * @return String a single String that is composed of multiple Strings, seperated by a newline
	 */
	public String getStringPatterns(){
		String text = "";
		for (Pattern p : getRelatedPatterns()){
			text += p + "\n";
		}
		return text;
	}
	
	public String toString(){
		return name;
	}
}
