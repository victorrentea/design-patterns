package victor.training.patterns.behavioral.observer;

import victor.training.patterns.behavioral.command.Barman;
import victor.training.patterns.stuff.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		myFrame.jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Button clicked");

				// INTERZI MUNCA GREA DE CPU sau WAIT dupa retea
				Barman barman = new Barman();
				CompletableFuture
					.supplyAsync(barman::pourVodka) // running on commonPool (not on EventDispatch thread)
					.thenAcceptAsync(vodka -> myFrame.jButton.setText(vodka.toString()), SwingUtilities::invokeLater);

			}
		});
		myFrame.jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Button clicked again");
			}
		});
	}
}

