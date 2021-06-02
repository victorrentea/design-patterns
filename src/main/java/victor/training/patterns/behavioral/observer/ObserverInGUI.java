package victor.training.patterns.behavioral.observer;

import victor.training.patterns.stuff.MyFrame;

import javax.swing.*;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();

		myFrame.jButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Button clicked"));
	}
}

