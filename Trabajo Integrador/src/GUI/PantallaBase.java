package GUI;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaBase implements ActionListener{
    JPanel panel;
    PantallaMenuPrueba PantallaMenu;
    PantallaInsumoPrueba PantallaInsumos;

    public void agregarComponentePane(Container pane) {
        panel = new JPanel(new CardLayout());

        PantallaMenuPrueba PantallaMenu = PantallaMenuPrueba.crearPantalla(panel);
        PantallaInsumoPrueba PantallaInsumos = PantallaInsumoPrueba.crearPantalla(panel);

        pane.add(panel, BorderLayout.CENTER);
    }
    private static void crearYmostrarUI() {
        JFrame frame = new JFrame("Trabajo Practico DIED");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Por que no EXIT_ON_CLOSE??

        PantallaBase demo = new PantallaBase();
        demo.agregarComponentePane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();

        CardLayout p = (CardLayout)(panel.getLayout());
        p.show(panel, button);
    }

    public static void main(String[] args) {
        PantallaBase.crearYmostrarUI();
    }
}