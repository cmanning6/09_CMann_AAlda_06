/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diningphilosophers;

/**
 *
 * @author chad
 */
import javax.swing.*;
import java.awt.*;

public class Philosopher implements Runnable {
    private final int id;
    private final Object leftFork;
    private final Object rightFork;
    private final int x;
    private final int y;
    private Graphics2D g = null;

    Philosopher(int id, Object left, Object right, Graphics2D g, int x, int y) {
        this.id = id;
        this.leftFork = left;
        this.rightFork = right;
        this.g = g;
        this.x = x;
        this.y = y;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        philosopherGUI.updateGUI(id, action);
        Thread.sleep((((int) (Math.random() * 1000))));
    }

    private void drawName () {
        g.drawString(Thread.currentThread().getName(), x, y);
     }

    @Override
    public void run() {
        drawName();

        try {
            while (true) {
                doAction("Thinking"); // thinking
                synchronized (leftFork) {
                    //doAction("Picked up left fork");
                    synchronized (rightFork) {
                        //doAction("Picked up right fork - eating"); // eating
                        //doAction("Put down right fork");
                        doAction("Eating");
                        doAction("Thinking");
                    }
                    //doAction("Put down left fork. Returning to thinking");
                    doAction("Thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class philosopherGUI extends JFrame
{
    JPanel p = new JPanel();
    Graphics2D g;
    static JLabel states[] = new JLabel[5];

    public philosopherGUI () {
        int x[] = {300, 500, 500, 100, 100};
        int y[] = {50, 150, 350, 350, 150};
        
        getContentPane().add(p);
        setSize(700, 700);
        p.setBackground(Color.black);
        setTitle("Dining Philosopher Problem: Multithread & Synchronization");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for(int i = 0; i < 5; ++i){
            states[i] = new JLabel("Thinking");
            states[i].setBounds(x[i], y[i], 200, 20);
        }

        g = (Graphics2D) p.getGraphics();
            g.setFont(new Font("Roman", Font.BOLD, 20));
        try { Thread.sleep(100); } catch(Exception e) {}
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setBackground(Color.black);
            g.setXORMode ( Color.white);
    }
    
    public static void updateGUI(int id, String action) {
        states[id].setText(action); 
    }
}