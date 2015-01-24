package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import control.Controller;

public class MainView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014982946105342479L;
	private JScrollPane resultScrollPane;
	private DefaultTableModel tableData = new DefaultTableModel();
	private JTable result;
	private DescriptionPanel description = new DescriptionPanel();
	
	public MainView() {		
		result = new JTable();
		resultScrollPane = new JScrollPane(result);
		
		//Fullscreen
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		add(new SelectPanel(this), BorderLayout.NORTH);				
		add(resultScrollPane, BorderLayout.CENTER);	
		add(description, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {  
			 public void windowClosing(WindowEvent e) {
				 Controller.getInstance().getImportExport().exportRepository(Controller.getInstance().getMasterComponent());   
			 }
		});
		result.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if (result.getSelectedRow() > -1) {
	            description.setPattern(Controller.getInstance().getPattern(result.getValueAt(result.getSelectedRow(), 0).toString()));
	        	}
	        }
	    });
		setTitle("Scope and Purpose Selector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);	
	}
	/**
	 * The function refreshes the JTable of found patterns
	 * @param scopeName the scope name of the pattern you'r looking for
	 * @param purposeName the purpose name of the patterns you'r looking for
	 */
	public void refreshTable(String scopeName,String purposeName){	
		//Get information to fill table
		Object[][] resultData = Controller.getInstance().getTableData(scopeName, purposeName);
		Object[] header = {"Pattern name","Problem","Consequences"};
		
		//set Data in Model
		tableData = new DefaultTableModel(resultData, header);
		//set Model in JTable
		result.setModel(tableData);
		resultScrollPane.revalidate();
		resultScrollPane.repaint();
		
		//Debug	
		System.out.println("Refreshing Table");
	}
}