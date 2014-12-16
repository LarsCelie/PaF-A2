package model;

import java.util.ArrayList;

public interface Component extends java.io.Serializable {
	/**
	 * @return the name
	 */
	public String getName();
	
	/**
	 * 
	 * @param component the component to add
	 */
	public void addComponent(Component component);
	
	/**
	 * 
	 * @param component the component to remove
	 */
	public void removeComponent(Component component);
	
	/**
	 * 
	 * @return the components from the parent
	 */
	public ArrayList<Component> getChilds();
	
	/**
	 * 
	 * @param name the specific name
	 * @return child with the specific name
	 */
	public Component getChild(String name);
}
