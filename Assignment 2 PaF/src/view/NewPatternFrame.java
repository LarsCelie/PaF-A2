package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Component;
import control.Controller;

public class NewPatternFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 3448124339252308452L;
	private JTextField nameField;
	private JTextArea solution, problems, consequences;
	private JPanel panel;
	private JScrollPane listScroller,problemScroller, consequenceScroller;
	private JComboBox<String> scope, purpose;
	private JButton save, imageButton;
	private BufferedImage img = null;
    private JFileChooser chooser;
    private FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
    private JList<String> relatedPatterns;
    private MainView mV;
 
    /**
     * constructor for making the new pattern frame
     */
	public NewPatternFrame(MainView mV){
		super("New Pattern");
		this.mV = mV;
		Controller con = Controller.getInstance();
		Component master = con.getMasterComponent();
		
		nameField = new JTextField();
		panel = new JPanel(new GridLayout(9, 2));
		scope = new JComboBox<String>();
		purpose = new JComboBox<String>();
		chooser = new JFileChooser(Controller.getInstance().getImportExport().getDirectory());
		chooser.setFileFilter(imageFilter);
		solution = new JTextArea();	
		problems = new JTextArea();
		consequences = new JTextArea();
		save = new JButton("Save Pattern");
		imageButton = new JButton("Select Diagram");
		relatedPatterns = new JList<String>(getPatternNames());
		problemScroller = new JScrollPane(problems);
		problemScroller.setPreferredSize(new Dimension(250, 80));
		consequenceScroller = new JScrollPane(consequences);
		consequenceScroller.setPreferredSize(new Dimension(250, 80));
		listScroller = new JScrollPane(relatedPatterns);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		fillComboBox(this.getChildNames(master), scope);		
		for(Component c : master.getChilds()){
			fillComboBox(getChildNames(c), purpose);
		}
		scope.setSelectedIndex(0);
		purpose.setSelectedIndex(0);
		relatedPatterns.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				
		panel.add(new JLabel("Pattern name: "));
		panel.add(nameField);
		panel.add(new JLabel("Scope: "));
		panel.add(scope);
		panel.add(new JLabel("Purpose: "));
		panel.add(purpose);
		panel.add(new JLabel("Solution: "));
		panel.add(solution);
		panel.add(new JLabel("DiagramFile: "));
		panel.add(imageButton);
		panel.add(new JLabel("Problems: "));
		panel.add(problemScroller);
		panel.add(new JLabel("Consequences: "));
		panel.add(consequenceScroller);
		panel.add(new JLabel("Related patterns: "));
		panel.add(listScroller);
		panel.add(save);
		add(panel);
		
		imageButton.addActionListener(this);
		save.addActionListener(this);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
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
	 * This function fills a ComboBox with an arraylist of objects
	 * @param arrayList a ArrayList of objects which to place in the ComboBox
	 * @param target a ComboBox which to fill with an array of objects
	 */
	public void fillComboBox(ArrayList<Object> arrayList, JComboBox target) {
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
	public ArrayList<Object> getChildNames(Component parent) {
		ArrayList<Object> names = new ArrayList<Object>();
		ArrayList<Component> children = parent.getChilds();
		for(Component child : children) {
			names.add(child.getName());
		}
		return names;
	}
	
	/**
	 * Method to get all patternnames, used for filling selection List
	 * @return array of Strings from all patternnames that exists
	 */
	public String[] getPatternNames(){
		ArrayList<String> patterns = new ArrayList<String>(); 
		for(Component scopeC : Controller.getInstance().getMasterComponent().getChilds()){
			for(Component purposeC : scopeC.getChilds()){
				for(Component patternC : purposeC.getChilds()){
					patterns.add(patternC.getName());
				}
			}
		}
		String[] patternNames = new String[patterns.size()];
		int i = 0;
		for(String s : patterns){
			patternNames[i] = s;
			i++;
		}
		return patternNames;
	}
	
	/**
	 * convert JTextArea to ArrayList<String> method
	 * @param input the JTextArea that needs to convert
	 * @return arraylist with strings from textarea
	 */
	public ArrayList<String> areaToArrayList(JTextArea input){
		String s[] = input.getText().split("\\r?\\n");
		return new ArrayList<>(Arrays.asList(s)) ;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==imageButton){
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            try{
            	if(file != null){
            		img = ImageIO.read(file);
            	}               
            }catch(IOException e1) {
            	
            }
        }
		if(e.getSource()==save){
			if(checkInput()){		
				ArrayList<String> relPatterns = new ArrayList<String>();
				for(Object o : relatedPatterns.getSelectedValuesList()){
					relPatterns.add(o.toString());
				}
				boolean b = Controller.getInstance().makePattern(scope.getSelectedItem().toString(), purpose.getSelectedItem().toString(), nameField.getText(), areaToArrayList(problems), areaToArrayList(consequences), solution.getText(), new ImageIcon(img), relPatterns);
				if(b){
					System.out.println("pattern added");	
					mV.refreshTable("Any", "Any");
				}
				else{
					System.out.println("not added, becuase already exists");
				}
			}else{
				JOptionPane.showMessageDialog( this, "Not all inpufields are correct","Error creating pattern", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * An String check
	 * @param input the String input to check
	 * @return false if NULL or trimmed empty otherwise true
	 */
	public boolean checkString(String input){
		if(input != null && !input.trim().equals(""))
			return true;
		return false;
	}
	
	/**
	 * Check if input is correct before making an Pattern
	 * @return true if input is correct
	 */
	public boolean checkInput(){
		boolean check = true;
		//empty nameField
		check = check && checkString(nameField.getText());
		check = check && checkString(problems.getText());
		check = check && checkString(consequences.getText());
		check = check && checkString(solution.getText());
		check = check && (img != null);
		return check;
	}
}
