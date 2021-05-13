package victor.training.patterns.behavioral.observer;

import victor.training.patterns.stuff.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();


		ActionListener listener = new Constantinescu();
		ActionListener listener2 = new Aglae();

		myFrame.jButton.addActionListener(listener);
		myFrame.jButton.addActionListener(listener2);
	}

	private static class Constantinescu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Button clicked");
		}
	}

	private static class Aglae implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Button clicked");
		}
	}
}

