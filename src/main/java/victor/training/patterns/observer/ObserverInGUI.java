package victor.training.patterns.observer;

import org.springframework.core.task.TaskExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.runAsync;

public class ObserverInGUI {
	private static final SwingExecutor swingExecutor = new SwingExecutor();
	public static final ExecutorService backgroundExecutor = Executors.newCachedThreadPool();
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
		myFrame.jButton.addActionListener(e -> runAsync(ObserverInGUI::handleCLick, backgroundExecutor));
	}
//				CompletableFuture.supplyAsync(() -> callApi(), backgroundExecutor)
//						.thenApply(ObserverInGUI::processResponse)
	// 					.exceptionally(shitty)
//						.thenAcceptAsync(data -> JOptionPane.showMessageDialog(null, data),
//								swingExecutor);

	private static void handleCLick() {
		String data = callApi(); //or CPU work
		String processedData = processResponse(data);
		SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(null, processedData)); // must run on UI thread
	}

	private static String processResponse(String data) {
		return "Got data clicked : " + data;
	}

	private static String callApi() {
		try {
			Thread.sleep(4_000);
			return "data";
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
}


//<editor-fold desc="SWING">

class MyFrame extends JFrame {

	public JButton jButton = new JButton("Press Me");
	public JPanel panel1 = new JPanel();
	public JPanel panel2 = new JPanel();
	public JTextArea textArea = new JTextArea(3, 8);
	public JTextField textField = new JTextField(8);

	public MyFrame() {

		getContentPane().setLayout(new GridLayout(1, 2, 20, 20));
		getContentPane().add(jButton);
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
//</editor-fold>
