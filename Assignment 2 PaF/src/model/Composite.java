package model;

import java.util.ArrayList;

public class Composite implements Component, java.io.Serializable {
	private static final long serialVersionUID = -4599611076659574174L;
	private String name;
	private ArrayList<Component> childs;
	
	/**
	 * the first constructor that makes an empty childs arraylist
	 * @param String the name of the composite
	 */
	public Composite(String name){
		this.name = name;
		this.childs = new ArrayList<Component>();
	}
	
	/**
	 * @return String name
	 */
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void addComponent(Component component) {
		if(!childs.contains(component)){
			this.childs.add(component);
		}		
		System.out.println(component.getName()+" added into " + this.getName());
	}

	@Override
	public void removeComponent(Component component) {
		this.childs.remove(component);
	}
	
	@Override
	public ArrayList<Component> getChilds() {
		return this.childs;
	}

	@Override
	public Component getChild(String name) {
		Component c = null;
		for(Component child : childs){
			if(child.getName().equals(name))
				c = child;
		}
		return c;
	}
	
	@Override
	public boolean equals(Object object){
		boolean isEqual = false;
		if(object != null && object instanceof Composite){
			Composite c = (Composite) object;
			isEqual = this.getName().equals(c.getName());
		}
		return isEqual;		
	}
	
	public String toString(){
		return this.getName();
	}
}
