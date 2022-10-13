//package victor.training.patterns.command;
//
//import java.io.Console;
//import java.io.IOException;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.IntStream;
//
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//
//public class CommandGame {
//    public static void main(String[] args) throws IOException {
//
//        Console console = System.console();
//        int read = console.reader().read();
//        System.out.println(read);
//    }
//}
//
//class MovingObject{
//    int x=5,y=5;
//
//    public void moveUp() {
//        y++;
//        y%=10;
//    }
//
//    public void moveDown() {
//        y--;
//        y%=10;
//    }
//
//    public void moveLeft() {
//        x--;
//        x%=10;
//    }
//
//    public void moveRight() {
//        x++;
//        x%=10;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}
//class Game {
//
//
//    public static void main(String[] args) {
////        Scanner scanner = new Scanner(System.in);
////        while (scanner.hasNext()) {
////            String line = scanner.next();
////            line.chars().forEach(ch -> {
////                switch (ch) {
////                    case 'w' :
////                }
////            });
////        }
//        String userInput = "wwwuddusauuuuuwwddssaaw";
//        for (int i = 0; i < userInput.length(); i++) {
//
//        }
//        printBoard(5,5);
//    }
//    public static void printBoard(int x, int y) {
//        List<StringBuilder> boardLines = IntStream.range(0, 10).mapToObj(i -> new StringBuilder(" ".repeat(10))).collect(toList());
//        boardLines.get(10-y).setCharAt(x, '%');
//        System.out.println("-".repeat(12) + "\n"
//                           + boardLines.stream().map(sb -> "|" + sb + "|\n").collect(joining())
//                           + "-".repeat(12) + "\n");
//
//
//    }
//}
//
//
//interface Command {
//    String getName();
//    void execute();
//    void undo();
//}
////class