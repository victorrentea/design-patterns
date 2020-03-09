package victor.training.oo.structural.proxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DecoratorsInJDK {

    public static void main(String[] args) throws IOException {

        Writer fileWriter = new FileWriter("a.txt");

        fileWriter = new BufferedWriter(fileWriter);
    }
}
