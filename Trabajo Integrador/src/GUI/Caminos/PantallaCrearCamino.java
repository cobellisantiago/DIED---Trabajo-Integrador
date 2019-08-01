package GUI.Caminos;

import Dominio.Planta;
import GUI.Insumos.PantallaCrearInsumo;
import GUI.Insumos.PantallaInsumo;
import Gestores.GestorInsumos;
import Gestores.GestorPlantas;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PantallaCrearCamino implements ActionListener {

    private static PantallaCrearCamino single;
    JPanel panelGeneral;
    JDialog dialog;
    JComboBox<String> origen;
    JComboBox<String> fin;
    JTextField distancia;
    JTextField tiempo;
    JTextField pesoMax;
    String plantaOrigen;
    String plantaFin;

    final static String CREAR = "Crear Insumo";
    final static String DESCRIP = "Descripcion";
    final static String COSTO = "Costo por unidad";
    final static String REFRIGE = "Necesita refrigeracion?";
    final static String PESO = "Peso";
    //final static String UNIDAD = "Unidad";

    private PantallaCrearCamino(){}

    public static void crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCrearCamino();
        }
        single.dialog = new JDialog();
        single.origen = new JComboBox<>();
        single.fin = new JComboBox<>();
        single.distancia = new JTextField(20);
        single.tiempo = new JTextField(20);
        single.pesoMax = new JTextField(20);
        single.agregarPantalla(p);
    }

    private void agregarPantalla(JPanel p) {
        panelGeneral = new JPanel(new MigLayout(" fill, insets 0"));

        JPanel panelDatos = new JPanel (new MigLayout(""));
        panelDatos.setBackground(Color.white);
        JLabel tituloLabel = new JLabel("Crear Camino");
        JLabel origenLabel = new JLabel("Origen: ");
        JLabel finLabel = new JLabel("Fin: ");
        JLabel distanciaLabel = new JLabel("Distancia: ");
        JLabel tiempoLabel = new JLabel("Tiempo: ");
        JLabel pesoMaxLabel = new JLabel("Peso maximo soportado: ");

        tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
        tituloLabel.setForeground(Color.BLUE);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

        for (Planta a: Planta.getInstances()) {
            origen.addItem(a.getNombre());
        }

        for (Planta a: Planta.getInstances()) {
            fin.addItem(a.getNombre());
        }

        origen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                plantaOrigen = (String)cb.getSelectedItem();
                origen.getModel().setSelectedItem(plantaOrigen);
                fin.removeItem(plantaOrigen);
            }
        });

        fin.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                plantaFin = (String)cb.getSelectedItem();
                fin.getModel().setSelectedItem(plantaFin);

            }
        });

        origen.setSelectedItem(null);
        fin.setSelectedItem(null);


        panelDatos.add(origenLabel,"align label");
        panelDatos.add(origen, "wrap");
        panelDatos.add(finLabel,"align label");
        panelDatos.add(fin,"wrap");
        panelDatos.add(distanciaLabel,"align label");
        panelDatos.add(distancia, "wrap");
        panelDatos.add(tiempoLabel,"align label");
        panelDatos.add(tiempo,"wrap");
        //panel.add(new JLabel(UNIDAD));
        //panel.add(unidad);
        panelDatos.add(pesoMaxLabel,"align label");
        panelDatos.add(pesoMax,"wrap");

        JButton crear = new JButton("Crear");
        crear.addActionListener(this);
        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);

        panelDatos.add(cancelar,"tag cancelar, span, split 2, right, gaptop 5, sizegroup bttn");
        panelDatos.add(crear,"tag crear, sizegroup bttn");

        panelDatos.setBorder(BorderFactory.createRaisedBevelBorder());

        panelGeneral.add(panelDatos,"span, center, wrap");
        panelGeneral.setBackground(new Color(207,216,220));

        p.add(panelGeneral,"CrearCamino");

    }

    public void popUpCorrecto(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        this.dialog.setSize(400, 200);

        JLabel preguntaLabel= new JLabel("Insumo creado correctamente");
        preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
        panel.add(preguntaLabel,"center,span, wrap");


        JButton aceptar = new JButton("Crear otro insumo");

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        JButton cancelar = new JButton("Volver a Insumos");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = (JPanel)panelGeneral.getParent();
                CardLayout pane = (CardLayout)(p.getLayout());
                pane.show(p, "Insumos");
                //dialog.setVisible(false);
                dialog.dispose();
            }
        });

        panel.add(cancelar,"left, gapleft 25");
        panel.add(aceptar,"right, gapright 25");

        this.dialog.add(panel);

        this.dialog.setModal(true);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        if(button == "Crear") {
            if (!((String)origen.getSelectedItem()).isEmpty() && !((String)fin.getSelectedItem()).isEmpty() && !tiempo.getText().isEmpty()&& !distancia.getText().isEmpty()
                    && !pesoMax.getText().isEmpty()) {
                //GestorInsumos.getGestor().crear(descrip.getText(), Double.valueOf(costo.getText()), Double.valueOf(peso.getText()), refrige.isSelected());
                popUpCorrecto();
                //PantallaInsumo.getSingle().actualizarTablaInsumos();
                //PantallaInsumo.getSingle().actualizarTablaInsumosBuscar(PantallaInsumo.getSingle().panel);
            } //else descrip.setText(DESCRIP);
        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Caminos");
        }
    }

}
