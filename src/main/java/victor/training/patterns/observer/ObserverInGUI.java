package victor.training.patterns.observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		myFrame.oldLady.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Button clicked");
			}
		});
		myFrame.oldLady.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Button clicked2");
			}
		});
	}
}

