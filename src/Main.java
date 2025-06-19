import ajedrez.vista.TableroGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TableroGUI().setVisible(true));
    }
}