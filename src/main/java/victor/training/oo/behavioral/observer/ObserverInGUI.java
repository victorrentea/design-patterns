package victor.training.oo.behavioral.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import victor.training.oo.structural.composite.MyFrame;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		// SOLUTION(
		myFrame.button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Button clicked");
			}
		});
		// SOLUTION)
		//TODO myFrame.button1.addActionListener(new ActionListener);
	}
}

