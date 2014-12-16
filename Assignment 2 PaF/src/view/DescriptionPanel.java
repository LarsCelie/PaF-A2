package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Pattern;
import control.Controller;

public class DescriptionPanel extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7026252796725054006L;
	private Pattern pattern;
	private JTextField nameField;
	private JTextArea problemField,solutionField ,consequenceField, relatedField;
	private JButton edit,pickImage,diagramIcon;
	private ImageIcon diagram;
	private JFileChooser chooser;
	private FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
	
	
	/**
	 * Shows all information about the pattern that is selected by the SelectPanel
	 */
	public DescriptionPanel(){
		edit = new JButton("Edit");
		pickImage = new JButton("Select image");
		
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		chooser = new JFileChooser(Controller.getInstance().getImportExport().getDirectory());
		chooser.setFileFilter(imageFilter);		
		
		JLabel name = new JLabel("Pattern name");
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(2,2,2,2);
		add(name, c);
		
		nameField = new JTextField(10);
		nameField.setEnabled(false);
		c.gridx = 1;
		c.gridy = 0;
		add(nameField, c);
		
		JLabel solution = new JLabel("Solution");
		c.gridx = 0;
		c.gridy = 1;
		add(solution, c); 
		
		solutionField = new JTextArea(2,10);
		solutionField.setEnabled(false);
		JScrollPane scrollSolution = new JScrollPane(solutionField);
		c.ipady = 70;
		c.gridx = 1;
		c.gridy = 1;
		add(scrollSolution, c);
		
		JLabel problem = new JLabel("Problem(s)");
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 2;
		add(problem, c);
		
		problemField = new JTextArea(2,10);
		problemField.setEnabled(false);
		JScrollPane scrollProblem = new JScrollPane(problemField);
		c.ipady = 70;
		c.gridx = 1;
		c.gridy = 2;
		add(scrollProblem, c);
		
		JLabel consequence = new JLabel("Consequence(s)");
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 3;
		add(consequence, c); 
		
		consequenceField = new JTextArea(2,10);
		consequenceField.setEnabled(false);
		JScrollPane scrollConsequence = new JScrollPane(consequenceField);
		c.ipady = 70;
		c.gridx = 1;
		c.gridy = 3;
		add(scrollConsequence, c);
		
		JLabel related = new JLabel("Related patttern(s)");
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 4;
		add(related, c); 
		
		relatedField = new JTextArea("related patterns");
		relatedField.setEnabled(false);
		c.ipady = 40;
		c.gridx = 1;
		c.gridy = 4;
		add(relatedField, c);
		
		JLabel diagramName = new JLabel("Diagram");
		c.gridx = 0;
		c.gridy = 5;
		add(diagramName, c);
		diagramIcon = new JButton("Show diagram");
		c.gridx = 1;
		c.gridy = 5;
		add(diagramIcon, c);
		diagramIcon.addActionListener(this);
		
		c.gridy = 6;
		c.gridx = 0;
		add(edit, c);
		c.gridx = 1;
		c.gridy = 6;
		add(pickImage);
		
		pickImage.addActionListener(this);
		edit.addActionListener(this);
	}
	
	
	/**
	 * Sets a new pattern which in turn updates all with the current pattern information
	 * @param Pattern pat
	 */
	public void setPattern(Pattern pat){
		pattern = pat;
		
		nameField.setText(pattern.getName());
		solutionField.setText(pattern.getSolution());
		problemField.setText(pattern.getStringProblems());
		consequenceField.setText(pattern.getStringConsequences());
		if (pattern.getRelatedPatterns() != null){
			relatedField.setText(pattern.getStringPatterns());
		} else {
			relatedField.setText("");
		}
		if (pattern.getDiagram() != null){
			diagram = pattern.getDiagram();
		} else {
			diagram = new ImageIcon();
		}
//		this.revalidate();
		//this.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == edit){
			if (edit.getText().equals("Edit")){
				
				edit.setText("Save");
				nameField.setEnabled(true);
				solutionField.setEnabled(true);
				problemField.setEnabled(true);
				consequenceField.setEnabled(true);
			} else {
				edit.setText("Edit");
				
				nameField.setEnabled(false);
				solutionField.setEnabled(false);
				problemField.setEnabled(false);
				consequenceField.setEnabled(false);
				
				Controller.getInstance().editPattern(pattern, nameField.getText(), areaToArrayList(solutionField), areaToArrayList(consequenceField), solutionField.getText(), diagram);
			}
		}
		if(e.getSource()==pickImage){
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            try{
            	if(file != null){
            		diagram = new ImageIcon(ImageIO.read(file));
            	}               
            }catch(IOException e1) {
            	
            }
        }
		if(e.getSource()==diagramIcon){
			new ImageFrame(diagram);
		}
	}

	public ArrayList<String> areaToArrayList(JTextArea input){
		String s[] = input.getText().split("\\r?\\n");
		return new ArrayList<>(Arrays.asList(s)) ;
	}
}
