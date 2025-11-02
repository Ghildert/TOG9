package msc;
import java.awt.*;
import javax.swing.*;

public class ButtonFunctionExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Functions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT)); // 👈 align to left

        JButton helloButton = new JButton("Say Hello");
        JButton exitButton = new JButton("Exit");

        helloButton.addActionListener(e -> sayHello());
        exitButton.addActionListener(e -> exitApp());

        frame.add(helloButton);
        frame.add(exitButton);
        frame.setVisible(true);
    }

    static void sayHello() {
        JOptionPane.showMessageDialog(null, "Hello there!");
    }

    static void exitApp() {
        System.exit(0);
    }
}
