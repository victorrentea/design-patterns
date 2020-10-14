package victor.training.patterns.behavioral.observer;

import victor.training.patterns.stuff.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverInGUI { 
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		// SOLUTION(
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Button clicked");
			}
		};
		myFrame.button1.addActionListener(listener);
		// SOLUTION)
		//TODO myFrame.button1.addActionListener(new ActionListener);
	}
}

