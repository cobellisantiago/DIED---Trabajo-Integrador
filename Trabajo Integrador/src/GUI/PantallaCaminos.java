package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaCaminos implements ActionListener{
    private static PantallaCaminos single;
    JPanel panel;
    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaCaminos(){}

    public static PantallaCaminos crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCaminos();
            single.agregarPantalla(p);
        }
        return single;
    }

    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        panel = new JPanel();
        JButton menu1 = new JButton(MENU);
        menu1.addActionListener(this);

        panel.add(menu1);
        panel.add(new JButton(CREAR));
        panel.add(new JButton(BUSCAR));
        panel.add(new JButton(EDITAR));
        panel.add(new JButton(BORRAR));

        p.add(panel, "Caminos");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        if(button == MENU) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);
        }
        else {
            //Operaciones de crear, buscar, editar y borrar.
        }
    }
}
