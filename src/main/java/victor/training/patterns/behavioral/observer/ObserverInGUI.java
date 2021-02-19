package victor.training.patterns.behavioral.observer;

import victor.training.patterns.stuff.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverInGUI {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();

		myFrame.baba.addActionListener(e -> JOptionPane.showMessageDialog(null, "Button clicked"));

		myFrame.baba.addActionListener(new VasilescuDeLa2());
	}

}

class VasilescuDeLa2 implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
//			ExecutorService pool = Executors.newFixedThreadPool(2);
//			pool.submit()

		try {
			Thread.sleep(10000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
//			JOptionPane.showMessageDialog(null, "Button clicked2");
	}
}

