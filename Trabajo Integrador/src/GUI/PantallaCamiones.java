package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaCamiones implements ActionListener{
    private static PantallaCamiones single;
    JPanel panel;
    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaCamiones(){}

    public static PantallaCamiones crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCamiones();
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

        p.add(panel, "Camiones");
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
