package GUI;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaInsumoPrueba implements ActionListener{
    private static PantallaInsumoPrueba single;
    JPanel panel;
    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaInsumoPrueba(){}

    public static PantallaInsumoPrueba crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaInsumoPrueba();
            single.agregarPantalla(p);
        }
        return single;
    }

    public void agregarPantalla(JPanel p) {
        panel = new JPanel();
        JButton menu1 = new JButton(MENU);
        menu1.addActionListener(this);

        panel.add(menu1);
        panel.add(new JButton(CREAR));
        panel.add(new JButton(BUSCAR));
        panel.add(new JButton(EDITAR));
        panel.add(new JButton(BORRAR));

        p.add(panel, "Insumos");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        JPanel p = (JPanel)panel.getParent();

        CardLayout pane = (CardLayout)(p.getLayout());
        pane.show(p, button);
    }
}