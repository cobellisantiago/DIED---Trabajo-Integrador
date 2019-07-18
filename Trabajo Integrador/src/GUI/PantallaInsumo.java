package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaInsumo implements ActionListener{
    private static PantallaInsumo single;
    JPanel panel;
    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaInsumo(){}

    public static PantallaInsumo crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaInsumo();
            single.agregarPantalla(p);
        }
        return single;
    }

    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        panel = new JPanel();
        JButton menu = new JButton(MENU);
        menu.addActionListener(this);
        JButton crear = new JButton(CREAR);
        crear.addActionListener(this);

        panel.add(menu);
        panel.add(crear);
        panel.add(new JButton(BUSCAR));
        panel.add(new JButton(EDITAR));
        panel.add(new JButton(BORRAR));
        //panel.add(new JTable(2,2));

        p.add(panel, "Insumos");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        if(button == MENU) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);
        }
        else if(button == CREAR) {
            PantallaCrearInsumo.crearPantalla();
        }
    }
}
