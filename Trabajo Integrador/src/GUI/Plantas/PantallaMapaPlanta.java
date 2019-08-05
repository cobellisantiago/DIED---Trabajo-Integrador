package GUI.Plantas;

import GUI.Grafo.PanelGrafoPlantas;
import Gestores.GestorInsumos;
import Gestores.GestorPlantas;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Stock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
        panelGeneral.setLayout(new MigLayout("debug, fill, insets 0",""));
        JPanel panelGrafo = PanelGrafoPlantas.getSingle();
        //panelGrafo.setLayout(new MigLayout("debug, fill","[][grow]"));
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
        
        JLabel cbLabel = new JLabel("Id Insumo");
        
        JComboBox<Integer> cb = new JComboBox<Integer>();
        
        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox<Integer> c = (JComboBox<Integer>)e.getSource();
                PanelGrafoPlantas grafo = PanelGrafoPlantas.getSingle();
                
                grafo.setNormalColor();
                if(c.getSelectedItem() != null) {
                	Insumo i = GestorInsumos.getGestor().getInsumo((Integer)c.getSelectedItem());
                	for(Stock s : Stock.getInstances()) {
                		if(s.getInsumo().equals(i) && !(s.getPuntoPedido() < s.getCantidad())) {
                			 grafo.getVertice(s.getPlanta().getId()).setColor(Color.GREEN);
                		}
                	}
                	
                }
            	grafo.repaint();
            }
        });
        
        cb.addItem(null);
        
        for(Insumo i : Insumo.getInstances())	cb.addItem(i.getId());

        cb.setSelectedItem(null);
        
        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        //panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(cbLabel, "span,split 3,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(cb, "sizegroup bttn");

        panelGeneral.add(panelBotones,"span, wrap, grow");
        panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelGrafo.setBorder(BorderFactory.createTitledBorder("Panel de Grafo"));
        panelGeneral.add(panelGrafo, "span, grow");

        p.add(panelGeneral,"MapaPlanta");
    }


    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getName();
        if (button == "Plantas") {
            JPanel p = (JPanel) panelGeneral.getParent();
            CardLayout pane = (CardLayout) (p.getLayout());
            PanelGrafoPlantas.deleteSingle();
            pane.show(p, button);
        }
    }

}
