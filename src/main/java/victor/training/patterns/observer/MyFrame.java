package victor.training.patterns.observer;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	public JButton oldLady = new JButton("Press Me");
	public JPanel panel1 = new JPanel();
	public JPanel panel2 = new JPanel();
	public JTextArea textArea = new JTextArea(3, 8);
	public JTextField textField = new JTextField(8);

	public MyFrame() {

		getContentPane().setLayout(new GridLayout(1, 2, 20, 20));
		getContentPane().add(oldLady);
		getContentPane().add(panel1);
		panel1.add(panel2);
		panel2.add(textArea);
		panel1.add(textField);

		setTitle("OO example: Observer, Composite, TemplateMethod");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
	}

}
