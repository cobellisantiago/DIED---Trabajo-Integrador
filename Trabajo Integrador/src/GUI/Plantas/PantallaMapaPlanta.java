package GUI.Plantas;

import GUI.Grafo.PanelGrafoPlantas;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaMapaPlanta implements ActionListener {

    private static PantallaMapaPlanta single;
    public JPanel panelGeneral;

    private PantallaMapaPlanta(){}

    public static void crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaMapaPlanta();
        }
        single.panelGeneral = new JPanel();
        single.agregarPantalla(p);
    }

    public static PantallaMapaPlanta getSingle() {
    	return single;
    }
    
    public JPanel getPanelGeneral() {
    	return panelGeneral;
    }
    
    private void agregarPantalla(JPanel p) {

        JPanel panelGrafo = new PanelGrafoPlantas();

        ImageIcon backIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/left-arrow.png").getImage()
                .getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        JButton menu = new JButton(backIcon);
        menu.setBorderPainted(false);
        menu.setBorder(null);
        menu.setFocusable(false);
        menu.setMargin(new Insets(0, 0, 0, 0));
        menu.setContentAreaFilled(false);
        menu.setName("Plantas");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.addActionListener(this);
        menu.setVerticalTextPosition(JButton.TOP);
        menu.setHorizontalTextPosition(JButton.CENTER);

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        //panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");

        panelGeneral.add(panelBotones,BorderLayout.NORTH);
        panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelGrafo.setBorder(BorderFactory.createTitledBorder("Panel de Grafo"));
        panelGeneral.add(panelGrafo,BorderLayout.SOUTH);

        p.add(panelGeneral,"MapaPlanta");
    }


    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getName();
        if (button == "Plantas") {
            JPanel p = (JPanel) panelGeneral.getParent();
            CardLayout pane = (CardLayout) (p.getLayout());
            pane.show(p, button);
        }
    }

}
