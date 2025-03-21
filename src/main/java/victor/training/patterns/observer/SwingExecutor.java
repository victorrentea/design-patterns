package victor.training.patterns.observer;

import javax.swing.*;
import java.util.concurrent.Executor;

public class SwingExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        SwingUtilities.invokeLater(command);
    }
}