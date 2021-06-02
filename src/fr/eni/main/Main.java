package fr.eni.main;

import fr.eni.ihm.GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        JFrame app = new GUI();
                    }
                }
        );
    }
}
