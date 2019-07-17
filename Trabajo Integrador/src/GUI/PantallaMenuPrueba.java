package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaMenuPrueba implements ActionListener{
    private static PantallaMenuPrueba single;
    JPanel panel;
    final static String INSUMOS = "Insumos";
    final static String PLANTAS = "Plantas";
    final static String STOCK = "Stock de Insumos";
    final static String CAMINOS = "Caminos";
    final static String CAMIONES = "Camiones";
    final static String INFORMACION = "Informacion";

    private PantallaMenuPrueba(){}

    public static PantallaMenuPrueba crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaMenuPrueba();
            single.agregarPantalla(p);
        }
        return single;
    }

    public void agregarPantalla(JPanel p) {
        panel = new JPanel();
        JButton insumos = new JButton(INSUMOS);
        insumos.addActionListener(this);

        panel.add(insumos);
        panel.add(new JButton(PLANTAS));
        panel.add(new JButton(STOCK));
        panel.add(new JButton(CAMINOS));
        panel.add(new JButton(CAMIONES));
        panel.add(new JButton(INFORMACION));

        p.add(panel, "Menu");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        JPanel p = (JPanel)panel.getParent();

        CardLayout pane = (CardLayout)(p.getLayout());
        pane.show(p, button);
    }
}
