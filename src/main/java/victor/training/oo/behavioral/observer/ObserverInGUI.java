package victor.training.oo.behavioral.observer;

import victor.training.oo.stuff.MyFrame;

import javax.swing.*;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		// SOLUTION(
		myFrame.button1.addActionListener(e ->
				JOptionPane.showMessageDialog(null, "Button clicked"));
		// SOLUTION)
		//TODO myFrame.button1.addActionListener(new ActionListener);
	}
}

