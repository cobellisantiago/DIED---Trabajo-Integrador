package GUI;

import net.miginfocom.swing.MigLayout;

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
        panel = new JPanel(new BorderLayout(10,30));

        JButton menu = new JButton(MENU);
        menu.addActionListener(this);
        JButton crear = new JButton(CREAR);
        crear.addActionListener(this);

        JPanel panelBotones = new JPanel();
        panelBotones.add(menu);
        panelBotones.add(crear);
        panelBotones.add(new JButton(BUSCAR));
        panelBotones.add(new JButton(EDITAR));
        panelBotones.add(new JButton(BORRAR));

        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10, 5, 5, 10);
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panel.add(panelBotones,BorderLayout.NORTH);

        c.fill=GridBagConstraints.BOTH;
        c.gridx=0;
        c.gridy=1;
        JPanel panel2 = new JPanel(new MigLayout("debug, fillx","[][grow][]"));
        panel2.add(new JTable(6,6), "span 2 3, grow , wrap");
        //panel2.add(new JButton("Test"),"grow");
        panel2.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        panel.add(panel2);
        JPanel panel3 = new JPanel(new MigLayout());

        panel3.add(new JLabel("First Name:"));
        panel3.add(new JTextField(30));
        panel3.add(new JLabel("Last Name:"),       "gap unrelated");
        panel3.add(new JTextField(30),   "wrap");
        panel3.add(new JLabel("Address"));
        panel3.add(new JTextField(),    "span, grow");
        panel.add(panel3);
        panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));

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
