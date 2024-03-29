package GUI.Plantas;

import GUI.Grafo.PanelGrafoPlantas;
import Gestores.GestorInsumos;
import Gestores.GestorPlantas;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import Dominio.Camino;
import Dominio.Insumo;
import Dominio.Planta;
import Dominio.Stock;
import Estructuras.GrafoPlanta;
import Estructuras.Vertice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

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
        panelGeneral.setLayout(new MigLayout(" fill, insets 0",""));
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
        
        JLabel cbLabelInsumo = new JLabel("Id Insumo");
        
        JComboBox<Integer> cbInsumo = new JComboBox<Integer>();
        
        /*cbInsumo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox<Integer> c = (JComboBox<Integer>)e.getSource();
                PanelGrafoPlantas grafo = PanelGrafoPlantas.getSingle();
                
                
                
                grafo.setNormalColor();
                if(c.getSelectedItem() != null) {
                    ArrayList<Integer> listaId = new ArrayList<Integer>();
                    ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
                	Insumo ins = GestorInsumos.getGestor().getInsumo((Integer)c.getSelectedItem());
                	for(Stock s : Stock.getInstances()) {
                		if(s.getInsumo().equals(ins) && !(s.getPuntoPedido() < s.getCantidad())) {
                			listaId.add(grafo.getVertice(s.getPlanta().getId()).getId());
                			grafo.getVertice(s.getPlanta().getId()).setColor(Color.GREEN);
                		}
                	}
                	
                	for(Integer id : listaId)	listaPlantas.add(GestorPlantas.getGestor().getPlanta(id));
                	
                	List<List<Vertice<Planta>>> mejoresCaminos = Camino.getGrafoPlanta().mejoresCaminos(listaPlantas);
                	
                	List<Vertice<Planta>> mejorCaminoL = mejoresCaminos.get(0);
                	List<Vertice<Planta>> mejorCaminoT = mejoresCaminos.get(1);
                	
                	//System.out.println(mejorCaminoL);
                	
                	for(int i = 0 ; i < mejorCaminoL.size()-1 ; i++) {
                		grafo.getArista(mejorCaminoL.get(i).getValor().getId(), mejorCaminoL.get(i+1).getValor().getId()).setColor(Color.RED);
                	}
                	
                	for(int i = 0 ; i < mejorCaminoT.size()-1 ; i++) {
                		grafo.getArista(mejorCaminoT.get(i).getValor().getId(), mejorCaminoT.get(i+1).getValor().getId()).setColor(Color.GREEN);
                	}
                	
                }
            	grafo.repaint();
            }
        });*/
        
        cbInsumo.addItem(null);
        
        for(Insumo i : Insumo.getInstances())	cbInsumo.addItem(i.getId());

        cbInsumo.setSelectedItem(null);
        
        JButton buttonCaminoLong = new JButton("Seleccionar mejor camino por longitud");
        buttonCaminoLong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelGrafoPlantas grafo = PanelGrafoPlantas.getSingle();
            	grafo.setNormalColor();
                if(cbInsumo.getSelectedItem() != null) {
                    ArrayList<Integer> listaId = new ArrayList<Integer>();
                    ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
                	Insumo ins = GestorInsumos.getGestor().getInsumo((Integer)cbInsumo.getSelectedItem());
                	for(Stock s : Stock.getInstances()) {
                		if(s.getInsumo().equals(ins) && !(s.getPuntoPedido() < s.getCantidad())) {
                			listaId.add(grafo.getVertice(s.getPlanta().getId()).getId());
                			grafo.getVertice(s.getPlanta().getId()).setColor(Color.GREEN);
                		}
                	}
                	
                	for(Integer id : listaId)	listaPlantas.add(GestorPlantas.getGestor().getPlanta(id));
                	
                	List<List<Vertice<Planta>>> mejoresCaminos = Camino.getGrafoPlanta().mejoresCaminos(listaPlantas);

                	List<Vertice<Planta>> mejorCaminoT = mejoresCaminos.get(1);

                	for(int i = 0 ; i < mejorCaminoT.size()-1 ; i++) {
                		grafo.getArista(mejorCaminoT.get(i).getValor().getId(), mejorCaminoT.get(i+1).getValor().getId()).setColor(Color.RED);
                	}
                }
                grafo.repaint();
            }});
        
        JButton buttonCaminoTiempo = new JButton("Seleccionar mejor camino por tiempo");
        buttonCaminoTiempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelGrafoPlantas grafo = PanelGrafoPlantas.getSingle();
            	grafo.setNormalColor();
                if(cbInsumo.getSelectedItem() != null) {
                    ArrayList<Integer> listaId = new ArrayList<Integer>();
                    ArrayList<Planta> listaPlantas = new ArrayList<Planta>();
                	Insumo ins = GestorInsumos.getGestor().getInsumo((Integer)cbInsumo.getSelectedItem());
                	for(Stock s : Stock.getInstances()) {
                		if(s.getInsumo().equals(ins) && !(s.getPuntoPedido() < s.getCantidad())) {
                			listaId.add(grafo.getVertice(s.getPlanta().getId()).getId());
                			grafo.getVertice(s.getPlanta().getId()).setColor(Color.GREEN);
                		}
                	}
                	
                	for(Integer id : listaId)	listaPlantas.add(GestorPlantas.getGestor().getPlanta(id));
                	
                	List<List<Vertice<Planta>>> mejoresCaminos = Camino.getGrafoPlanta().mejoresCaminos(listaPlantas);
                	
                	List<Vertice<Planta>> mejorCaminoL = mejoresCaminos.get(0);
                	
                	for(int i = 0 ; i < mejorCaminoL.size()-1 ; i++) {
                		grafo.getArista(mejorCaminoL.get(i).getValor().getId(), mejorCaminoL.get(i+1).getValor().getId()).setColor(Color.RED);
                	}
                }
                grafo.repaint();
            }});
        
        
        JLabel cbLabelPlantaF = new JLabel("Id Planta Fin");
        
        JComboBox<Integer> cbPlantaF = new JComboBox<Integer>();

        for(Planta pl : Planta.getInstances())	cbPlantaF.addItem(pl.getId());

        cbPlantaF.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                Integer plantaFin = (Integer)cb.getSelectedItem();
                cbPlantaF.getModel().setSelectedItem(plantaFin);

            }
        });

        cbPlantaF.setSelectedItem(null);
        
        JLabel cbLabelPlantaO = new JLabel("Id Planta Origen");
        
        JComboBox<Integer> cbPlantaO = new JComboBox<Integer>();

        for(Planta pl : Planta.getInstances())	cbPlantaO.addItem(pl.getId());

        cbPlantaO.addItemListener(new ItemListener() {
        	Integer plantaOrigen = null;
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                if(cbPlantaF.getItemCount() != Planta.getInstances().size())	
                	cbPlantaF.insertItemAt(plantaOrigen, plantaOrigen);
                plantaOrigen = (Integer)cb.getSelectedItem();
                cbPlantaO.getModel().setSelectedItem(plantaOrigen);
                cbPlantaF.removeItem(plantaOrigen);

            }
        });

        cbPlantaO.setSelectedItem(null);
        
        JComboBox<List<Integer>> cbCaminos = new JComboBox<List<Integer>>();
        
        JButton buttonCaminos = new JButton("Buscar caminos entre plantas");
        buttonCaminos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer pO = (Integer)cbPlantaO.getSelectedItem();
                Integer pF = (Integer)cbPlantaF.getSelectedItem();
                PanelGrafoPlantas.getSingle().setNormalColor();
                if(pO != null && pF != null) {
                	cbCaminos.removeAllItems();
                	List<List<Vertice<Planta>>> caminos = Camino.getGrafoPlanta().caminos(Camino.getGrafoPlanta().getNodo(GestorPlantas.getGestor().getPlanta(pO)), Camino.getGrafoPlanta().getNodo(GestorPlantas.getGestor().getPlanta(pF)));
                	for(List<Vertice<Planta>> camino : caminos) {
                		List<Integer> listaAux = new ArrayList<Integer>();
                		for(Vertice<Planta> ver : camino) {
                			listaAux.add(ver.getValor().getId());
                		}
                		cbCaminos.addItem(listaAux);
                	}
                }
            }
        });
        
        

        cbCaminos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                PanelGrafoPlantas.getSingle().setNormalColor();
                List<Integer> camino = (List<Integer>)cb.getSelectedItem();
                if(camino != null) {
                	for(int i = 0 ; i < camino.size()-1 ; i++) {
            			PanelGrafoPlantas.getSingle().getArista(camino.get(i), camino.get(i+1)).setColor(Color.RED);;
            		}	
                }
                PanelGrafoPlantas.getSingle().repaint();
            }
        });

        cbCaminos.setSelectedItem(null);
        
        JPanel panelBotones = new JPanel(new MigLayout("fill, insets 0",""));
        //panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left, gapright 20");
        panelBotones.add(cbLabelInsumo, " split 8");
        panelBotones.add(cbInsumo, "");

        //panelBotones.add(new JLabel(""), "");
        panelBotones.add(cbLabelPlantaO, "gapleft 15");
        panelBotones.add(cbPlantaO, "");

        panelBotones.add(cbLabelPlantaF, "gapleft 15");
        panelBotones.add(cbPlantaF, "");

        //panelBotones.add(new JLabel(""), "");
        panelBotones.add(new JLabel("Caminos"), "gapleft 15");
        panelBotones.add(cbCaminos, "wrap");

        panelBotones.add(buttonCaminoLong, "skip 1, split 3");
        panelBotones.add(buttonCaminoTiempo, "");
        panelBotones.add(buttonCaminos, "");

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
