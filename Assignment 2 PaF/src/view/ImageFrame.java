package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3392802731070580651L;

	/**
	 * Makes a new frame with the image that is given
	 * @param ImageIcon image
	 */
	public ImageFrame(ImageIcon image) {
		// TODO Auto-generated constructor stub
		JLabel img = new JLabel(image);
		add(img);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		pack(); setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}