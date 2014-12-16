package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Component;
import control.Controller;

public class SelectPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7228651866529443798L;
	private MainView implementer;
	private JComboBox<String> scope, purpose;
	private Component master = Controller.getInstance().getMasterComponent();

	public SelectPanel(MainView implementer) {
		this.implementer = implementer;
		scope = new JComboBox<String>();
		purpose = new JComboBox<String>();
		scope.addItem("Any");
		purpose.addItem("Any");
		scope.setSelectedIndex(0);
		purpose.setSelectedIndex(0);
		fillComboBox(this.getChildNames(master), scope);		
		for(Component c : master.getChilds()){
			fillComboBox(getChildNames(c), purpose);
		}
		scope.addActionListener(this);
		purpose.addActionListener(this);
		add(new JLabel("Scope:"));
		add(scope);
		add(new JLabel("Purpose:"));
		add(purpose);
		implementer.refreshTable(scope.getSelectedItem().toString(),purpose.getSelectedItem().toString());
	}
	
	/**
	 * This function checks if a item is already present in a JComboBox object
	 * @param obj the value to check if present
	 * @param target the JComboBox in which to check for the obj
	 * @return a boolean which tells if the function has found an existing obj of the same value
	 */
	private boolean checkIfExists(Object obj, JComboBox target) {
		boolean exists = false;
        int size = target.getItemCount();
        for(int i=0;i<size;i++) {
            Object element = target.getItemAt(i);
            if(element.toString().equals(obj.toString()))
            	exists = true;
        }		
		return exists;
	}
	/**
	 * This function fills a ComboBox with an ArrayList of objects
	 * @param arrayList a ArrayList of objects which to place in the ComboBox
	 * @param target a ComboBox which to fill with an array of objects
	 */
	private void fillComboBox(ArrayList<Object> arrayList, JComboBox target) {
		for(Object obj : arrayList) {
			if(!checkIfExists(obj, target)){
				target.addItem(obj);
			}
		}
	}
	/**
	 * This function returns all child Components allocated to a parent Component
	 * @param parent the Component of which to get the child objects
	 * @return all child Components of the parent
	 */
	private ArrayList<Object> getChildNames(Component parent) {
		ArrayList<Object> names = new ArrayList<Object>();
		ArrayList<Component> children = parent.getChilds();
		for(Component child : children) {
			names.add(child.getName());
		}
		return names;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		implementer.refreshTable(scope.getSelectedItem().toString(),purpose.getSelectedItem().toString());
	}
}