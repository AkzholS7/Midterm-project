package com.company;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	JFrame frame = new JFrame();
	Gameplay gameplay = new Gameplay();
	frame.setBounds(1,1,700,600);
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setTitle("Shooter");
	frame.setResizable(false);
	frame.setVisible(true);
	frame.add(gameplay);
    }
}
