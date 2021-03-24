package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean game = false;
    private int score = 0;
    private double speed = 1;
    private double acceleration = 0.03;

    private int a = 0;

    private String str;

    private int life = 4;

    private float h1 = 59;
    private float s1 = 100;
    private float b1 = 50;

    private int superX = 0;

    private int levelX = 0;

    private int textX = 10;
    private int textY = 50;

    private Timer timer;
    private int delay = 15;




    public Gameplay() throws IOException {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        str = GetWord(3);
    }


    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        //stars
        for(int i = 0;i < 100;i++) {
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.fillRect(stars(), stars(), 2, 2);
        }
        for(int i = 0;i < 100;i++) {
            g.setColor(Color.getHSBColor(h1+10,s1-5,b1));
            g.fillRect(stars(), stars(), 2, 2);
        }
        //level
        if (levelX < 80){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.drawRect(120,8,80,15);
            g.fillRect(120, 8, levelX, 15);
        }
        if(levelX >= 80){
            levelX = 0;
        }

        if(score<20){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,25));
            g.drawString("LEVEL 1",10,25);
        }else if(score<40){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,25));
            g.drawString("LEVEL 2",10,25);
        }else if(score<60){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,25));
            g.drawString("LEVEL 3",10,25);
        }else if(score<80){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,25));
            g.drawString("LEVEL 4",10,25);
        }else if(score<100){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,25));
            g.drawString("LEVEL 5",10,25);
        }else if(life == 1){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,25));
            g.drawString("INFINITIVE MODE",10,50);
        }

        //super power
        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.setFont(new Font("SansSerif",Font.BOLD,25));
        g.drawString("SP:",220,25);

        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.drawRect(270,8,100,15);
        g.fillRect(270, 8, superX, 15);

        //SCORE
        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.setFont(new Font("SansSerif",Font.BOLD,25));
        g.drawString("SCORE:",550,25);

        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.setFont(new Font("SansSerif",Font.BOLD,25));
        g.drawString(""+score,650,25);

        //LIFE
        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.setFont(new Font("SansSerif",Font.BOLD,25));
        g.drawString("LIFE:",400,25);

        if(life == 4){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.fillOval(460,10,16,16);
            g.fillOval(485,10,16,16);
            g.fillOval(510,10,16,16);
        }else if(life == 3){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.fillOval(460,10,16,16);
            g.fillOval(485,10,16,16);
            g.drawOval(510,10,16,16);
        }else if(life == 2){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.fillOval(460,10,16,16);
            g.drawOval(485,10,16,16);
            g.drawOval(510,10,16,16);
        }else {
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.drawOval(460,10,16,16);
            g.drawOval(485,10,16,16);
            g.drawOval(510,10,16,16);
        }

        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.drawLine(0,30,700,30);

        //words
        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.setFont(new Font("SansSerif",Font.BOLD,30));
        g.drawString(str,textX,textY);

        g.setColor(Color.getHSBColor(h1,s1,b1));
        g.drawLine(0,550,700,550);

        //start and restart
        if(!game && textY == 50){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("SansSerif",Font.BOLD,30));
            g.drawString("Press ENTER to start", 190,300);
        }

        if(textY >= 550 && life == 1 ){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over, Scores: " + score, 190,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press ENTER to Restart", 230,350);
            textY = 700;
        }
        if(textY >= 550 && life != 1){
            g.setColor(Color.getHSBColor(h1,s1,b1));
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press SPACE to continue", 190,300);
            life --;
            try {
                newWord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.dispose();
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {}
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(game){
            textY += speed;
            repaint();
        }
        if(textY >= 550 ){
            game = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

        if(a < str.length())
            if(e.getKeyChar() == str.charAt(a)){
                a += 1;}
            else{
                a = 0;
            }

        if(a == str.length()){
                try {
                    newWord();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!game){
                try {
                    str = GetWord(3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                game = true;
                textY = 50;
                score = 0;
                speed = 1;
                superX = 0;
                levelX = 0;
                acceleration = 0.2;
                life = 4;
            repaint();}
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && life!=0){
            if(superX == 100) {
                if (game) {
                    speed = 0;
                    superX = 0;
                }
            }

            game = true;
            repaint();
        }
    }
    //stars generator
    public int stars(){
        Random random = new Random();
        int a = random.nextInt(700);
        return a;
    }

    public String GetWord(int a) throws IOException {
        String word;
        Random ran = new Random();
        int alphNum;
        switch(a) {
            case 3:
                alphNum = ran.nextInt(500);
                word = Files.readAllLines(Paths.get("src/com/company/Words3.txt")).get(alphNum);
                break;
            case 4:
                alphNum = ran.nextInt(500);
                word = Files.readAllLines(Paths.get("src/com/company/Words4.txt")).get(alphNum);
                break;
            case 5:
                alphNum = ran.nextInt(500);
                word = Files.readAllLines(Paths.get("src/com/company/Words5.txt")).get(alphNum);
                break;
            case 6:
                alphNum = ran.nextInt(500);
                word = Files.readAllLines(Paths.get("src/com/company/Words6.txt")).get(alphNum);
                break;
            case 7:
                alphNum = ran.nextInt(500);
                word = Files.readAllLines(Paths.get("src/com/company/Words7.txt")).get(alphNum);
                break;
            default:
                alphNum = ran.nextInt(500);
                word = Files.readAllLines(Paths.get("src/com/company/Words8.txt")).get(alphNum);
                break;
        }
        return word;}

    public void newWord() throws IOException {

        if(score<20){
            str = GetWord(3);
        }else if(score<40){
            str = GetWord(4);
        }else if(score<60){
            str = GetWord(5);
        }else if(score<80){
            str = GetWord(6);
        }else if(score<100){
            str = GetWord(7);
        }else{
            str = GetWord(8);
        }
        Random rand = new Random();
        int xCor = rand.nextInt(580);
        s1 = rand.nextFloat();
        h1 = rand.nextFloat();
        score += speed+acceleration;
        textX = xCor+10;
        if(speed == 0){
            speed = 1+acceleration*score/5;
        }else{
        speed += acceleration;}
        textY = 50;
        levelX +=4;
        if(superX<100){
            superX += 10;}
        a = 0;
    }
}
