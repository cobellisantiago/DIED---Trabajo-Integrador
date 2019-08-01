package GUI.Caminos;

import net.miginfocom.swing.MigLayout;

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
        panel = new JPanel(new BorderLayout(10,10));

        JLabel tituloLabel = new JLabel("Caminos");
        tituloLabel.setFont(new Font("Roboto",Font.BOLD,32));


        ImageIcon backIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/left-arrow.png").getImage()
                .getScaledInstance(35, 35, Image.SCALE_DEFAULT));

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
        crear.setName("CrearCamino");
        crear.addActionListener(this);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setName(BUSCAR);
        buscarButton.addActionListener(this);

        JButton mapaButton = new JButton("Mapa");
        mapaButton.setName("MapaPlanta");
        mapaButton.addActionListener(this);

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 3,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(buscarButton,"tag buscar, sizegroup bttn");
        panelBotones.add(mapaButton,"tag mapa, sizegroup bttn");

        //panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        //panel.add(crearTablaPlantas(GestorPlantas.getGestor().listarPlantas()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));
        panel.setBackground(new Color(207,216,220));


        p.add(panel, "Caminos");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getName();
        if(button == "Menu") {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);
        }
        else if(button == "CrearCamino") {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);
            //Operaciones de crear, buscar, editar y borrar.
        }
    }
}
