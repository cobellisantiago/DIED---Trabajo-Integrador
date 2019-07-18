package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaBase implements ActionListener{
    JPanel panel;
    PantallaMenu pantallaMenu;
    PantallaInsumo pantallaInsumos;
    PantallaPlantas pantallaPlantas;
    PantallaStock pantallaStock;

    public void agregarComponentePane(Container pane) {
        panel = new JPanel(new CardLayout());

        PantallaMenu.crearPantalla(panel);
        PantallaInsumo.crearPantalla(panel);
        PantallaPlantas.crearPantalla(panel);
        PantallaStock.crearPantalla(panel);
        PantallaCaminos.crearPantalla(panel);
        PantallaCamiones.crearPantalla(panel);
        PantallaInformacion.crearPantalla(panel);

        pane.add(panel, BorderLayout.CENTER);
    }
    private static void crearYmostrarUI() {
        JFrame frame = new JFrame("Trabajo Practico DIED");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1080,720));
        frame.setMinimumSize(new Dimension(600, 400));

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