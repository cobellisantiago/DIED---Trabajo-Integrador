package GUI.Camiones;

import Gestores.GestorCamiones;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PantallaCrearCamion implements ActionListener {

    private static PantallaCrearCamion single;
    JPanel panelGeneral;
    JDialog dialog;
    JTextField id;
    JTextField marca;
    JTextField modelo;
    JTextField dominio;
    JTextField anio;
    JTextField costoPorKm;
    JTextField capacidad;
    JCheckBox liquido;
    final static String CREAR = "Crear Camion";
    final static String ID = "Id";
    final static String MARCA = "Marca";
    final static String MODELO = "Modelo";
    final static String DOMINIO = "Dominio";
    final static String ANIO = "Anio";
    final static String COSTOPORKM = "Costo por kilometro";
    final static String LIQUIDO = "¿Puede transportar liquidos?";
    final static String CAPACIDAD = "Capacidad";

    private PantallaCrearCamion(){}

    /**
     * @param p
     */
    public static void crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCrearCamion();
        }
        single.dialog = new JDialog();
        single.marca = new JTextField(15);
        single.modelo = new JTextField(15);
        single.dominio = new JTextField(15);
        single.anio = new JTextField(15);
        single.costoPorKm = new JTextField(15);
        single.capacidad = new JTextField(15);
        single.liquido = new JCheckBox();
        single.agregarPantalla(p);
    }

    private void agregarPantalla(JPanel p){
        panelGeneral = new JPanel(new MigLayout(" fill, insets 0"));

        JPanel panelDatos = new JPanel(new MigLayout(""));
        panelDatos.setBackground(Color.white);
        JLabel tituloLabel = new JLabel("Crear Camion");
        JLabel marcaLabel = new JLabel("Marca: ");
        JLabel modeloLabel = new JLabel("Modelo: ");
        JLabel dominioLabel = new JLabel("Dominio: ");
        JLabel anioLabel = new JLabel("Año: ");
        JLabel costoLabel = new JLabel("Costo por Kilometro: ");
        JLabel capacidadLabel = new JLabel("Capacidad: ");
        JLabel liquidoLabel = new JLabel("¿Puede transportar una carga liquida? ");

        tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
        tituloLabel.setForeground(Color.BLUE);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

        panelDatos.add(marcaLabel, "align label");
        panelDatos.add(marca, "wrap");
        panelDatos.add(modeloLabel, "align label");
        panelDatos.add(modelo, "wrap");
        panelDatos.add(dominioLabel, "align label");
        panelDatos.add(dominio, "wrap");
        panelDatos.add(anioLabel,"align label");
        panelDatos.add(anio, "wrap");
        panelDatos.add(costoLabel,"align label");
        panelDatos.add(costoPorKm, "wrap");
        panelDatos.add(capacidadLabel,"align label");
        panelDatos.add(capacidad, "wrap");
        panelDatos.add(liquidoLabel, "align label");
        panelDatos.add(liquido, "wrap");

        JButton crear = new JButton("Crear");
        crear.addActionListener(this);
        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);

        panelDatos.add(cancelar,"tag cancelar, span, split 2, right, gaptop 5, sizegroup bttn");
        panelDatos.add(crear,"tag crear, sizegroup bttn");

        panelDatos.setBorder(BorderFactory.createRaisedBevelBorder());

        panelGeneral.add(panelDatos,"span, center, wrap");
        panelGeneral.setBackground(new Color(207,216,220));

        p.add(panelGeneral,"CrearCamion");

    }

    public void popUpCorrecto(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        dialog.setSize(400, 200);

        JLabel preguntaLabel= new JLabel("Camion creado correctamente");
        preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
        panel.add(preguntaLabel,"center,span, wrap");


        JButton aceptar = new JButton("Crear otro camion");

        aceptar.addActionListener(e -> dialog.dispose());

        JButton cancelar = new JButton("Volver a Camiones");
        cancelar.addActionListener(e -> {
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Camiones");
            dialog.setVisible(false);
            //dialog.dispose();
        });

        panel.add(cancelar,"left, gapleft 25");
        panel.add(aceptar,"right, gapright 25");

        dialog.add(panel);

        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        if(button == "Crear") {
            if (!marca.getText().isEmpty() && !modelo.getText().isEmpty() && !dominio.getText().isEmpty() &&
                    !anio.getText().isEmpty() && !costoPorKm.getText().isEmpty() && !capacidad.getText().isEmpty()) {
                GestorCamiones.getGestor().crear(marca.getText(), modelo.getText(), Double.valueOf(dominio.getText()),
                        Integer.valueOf(anio.getText()), Double.valueOf(costoPorKm.getText()), Integer.valueOf(capacidad.getText()), liquido.isSelected());
                popUpCorrecto();
                PantallaCamiones.getSingle().actualizarTablaCamiones();
               actualizarValores();
                //PantallaInsumo.getSingle().actualizarTablaInsumosBuscar(PantallaInsumo.getSingle().panel);
            } else marca.setText(MARCA);


        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Camiones");
            actualizarValores();
        }
    }

    public void actualizarValores(){
        marca.setText("");
        modelo.setText("");
        dominio.setText("");
        anio.setText("");
        costoPorKm.setText("");
        capacidad.setText("");
        liquido.setSelected(false);
    }
}
