import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class checkFrame extends JFrame {

	public static JPanel panel;
	private JTextField textField;

	public checkFrame() {
		JPanel main = new JPanel();
		getContentPane().add(main);
		
		main.setLayout(null);
		
		 panel = new JPanel();
		panel.setBounds(50, 71, 675, 621);
		main.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(288, 545, 105, 27);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(117, 130, 430, 52);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(117, 220, 430, 286);
		panel.add(panel_1);
		
		setSize(800, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
