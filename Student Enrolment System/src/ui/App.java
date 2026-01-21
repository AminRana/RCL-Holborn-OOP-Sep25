package ui;

import javax.swing.*;

public class App {

    static void main() {


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(MainMenu::new);
    }
}
