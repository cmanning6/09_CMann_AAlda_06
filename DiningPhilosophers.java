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
public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        Philosopher[] philosophers = new Philosopher[5];
        philosopherGUI app = new philosopherGUI();
        String philospherName[] = {"SOCRATES", "PLATO", "PYTHAGORAS", "DESCARTES", "CONFUCIUS"};
        int x[] = {300, 500, 500, 100, 100};
        int y[] = {50, 150, 350, 350, 150};
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {

            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(i, rightFork, leftFork, app.g, x[i], y[i]); // The last philosopher picks up the right fork first
            } else {
                philosophers[i] = new Philosopher(i, leftFork, rightFork, app.g, x[i], y[i]);
            }

            Thread t = new Thread(philosophers[i], philospherName[i]);
            t.start();
        }
    }
}
