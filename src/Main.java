import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarEntry;

public class Main extends JPanel implements KeyListener {
    public static final int CELL_SIZE = 20;
    public static int width = 400;
    public static int height = 400;
    public static int row = height/CELL_SIZE;
    public static int column = width/CELL_SIZE;
    private Snack snack;
    private Fruit fruit;
    private Timer t;
    private static String direction;
    private boolean allowKeyPress;
    private int score;

    public Main(){
    reset();
    addKeyListener(this);
    }

    private void reset(){
        score = 0;
        if (snack != null){
            snack.getSnackBody().clear();
        }
        allowKeyPress = true;
        direction = "right";
        snack = new Snack();
        fruit = new Fruit(snack);
        setTimer();
    }

    private void setTimer(){
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 100);
    }
    @Override

    public void paintComponent(Graphics g){
        for ( int i = 1; i < snack.getSnackBody().size() - 1 ; i++){ //whether bite itself?
            if (snack.getSnackBody().get(i).x == snack.getSnackBody().get(0).x &&
                    snack.getSnackBody().get(i).y == snack.getSnackBody().get(0).y){
                allowKeyPress = false;
                t.cancel();
                t.purge();
                int respone = JOptionPane.showOptionDialog(this, "score: "+ score,"GAME OVER",
                        JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null, null, JOptionPane.YES_OPTION);
                switch (respone){
                    case JOptionPane.CLOSED_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.YES_OPTION:
                        reset();
                        return;// if return, the following code wouldn't execute.
                }
            }
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, 400, 400);
        snack.drawSnack(g);
        fruit.drawFruit(g);
        //remove snack tail and add a new head.
        int snackX = snack.getSnackBody().get(0).x; //get the old head first.
        int snackY = snack.getSnackBody().get(0).y;
        if (direction == "left"){
            snackX -= CELL_SIZE;
        } else if (direction == "right") {
            snackX += CELL_SIZE;
        } else if (direction == "up") {
            snackY -= CELL_SIZE;
        } else if (direction == "down") {
            snackY += CELL_SIZE;
        }
        //add new head
        Node newHead = new Node(snackX,snackY);
        snack.getSnackBody().add(0,newHead);
        //if the snake eats fruit, creating a new fruit and leaving the tail.
        if (snackX == fruit.x && snackY == fruit.y){
             fruit = new Fruit(snack);
             score++;
        }else {
            snack.getSnackBody().remove(snack.getSnackBody().size() - 1);
        }
        allowKeyPress = true;
        requestFocusInWindow();
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(width, height);
    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Snake Game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (allowKeyPress){
            if (k == 37 && !direction.equals("right")){
            direction = "left";
            } else if (k == 38 && !direction.equals("down")) {
            direction = "up";
            } else if (k == 39 && !direction.equals("left")) {
            direction = "right";
            } else if (k == 40 && !direction.equals("up")) {
            direction = "down";
            }
            allowKeyPress = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }


    }