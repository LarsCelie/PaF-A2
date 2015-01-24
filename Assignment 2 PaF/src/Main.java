import view.MainView;
import view.NewPatternFrame;
import control.Controller;
/**
 * 
 * @author Lars, Joery, Xander
 *
 */
public class Main {

	public static void main(String[] args) {
		Controller c = Controller.getInstance();
		MainView sps = new MainView();
		NewPatternFrame npf = new NewPatternFrame(sps);	
		/*Pattern mistake = c.getPattern("Bridge");
		Component master = c.getMasterComponent();
		Component child1 = master.getChild("Class scope");
		Component child2 = child1.getChild("Behavioral purpose");
		child2.removeComponent(mistake);*/
	}
}
