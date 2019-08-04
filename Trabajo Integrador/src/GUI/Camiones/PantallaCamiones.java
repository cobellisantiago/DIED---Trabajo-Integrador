package GUI.Camiones;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import GUI.PantallaBase;

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


    public void agregarPantalla(JPanel p) {
        /*panel = new JPanel();
        JButton menu1 = new JButton(MENU);
        menu1.addActionListener(this);

        panel.add(menu1);
        panel.add(new JButton(CREAR));
        panel.add(new JButton(BUSCAR));
        panel.add(new JButton(EDITAR));
        panel.add(new JButton(BORRAR));
        */

        panel = new JPanel(new BorderLayout(10,10));

        JLabel tituloLabel = new JLabel("Camiones");
        tituloLabel.setFont(new Font("Roboto",Font.BOLD,32));

        ImageIcon backIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/left-arrow.png").getImage()
                .getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        //funcionamiento de flecha de retroceso
        JButton menu = new JButton(backIcon);
        menu.setBorderPainted(false);
        menu.setBorder(null);
        menu.setFocusable(false);
        menu.setMargin(new Insets(0, 0, 0, 0));
        menu.setContentAreaFilled(false);
        menu.setName("Menu");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.addActionListener(this);
        menu.setVerticalTextPosition(JButton.TOP);
        menu.setHorizontalTextPosition(JButton.CENTER);

        JButton crear = new JButton(CREAR);
        crear.setName("Crear");
        crear.addActionListener(this);

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 3,center,gaptop 10,gapbottom 10, sizegroup bttn");

        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        panel.setBackground(new Color(207,216,220));

        p.add(panel, "Camiones");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getName();
        if(button == MENU) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Menu");
        }
        else if(button == CREAR) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "CrearCamion");
            //Operaciones de crear, buscar, editar y borrar.

        }
    }

    public static PantallaCamiones getSingle() {
        return single;
    }

}
