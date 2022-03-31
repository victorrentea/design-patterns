package victor.training.patterns.behavioral.observer;

import victor.training.patterns.stuff.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		myFrame.jButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Button clicked"));
		myFrame.jButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Button2 clicked"));
		myFrame.jButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Button3 clicked"));
	}
}

