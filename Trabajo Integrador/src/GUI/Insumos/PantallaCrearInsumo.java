package GUI.Insumos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Gestores.GestorInsumos;
import net.miginfocom.swing.MigLayout;

public class PantallaCrearInsumo implements ActionListener{
    private static PantallaCrearInsumo single;
    JPanel panelGeneral;
    JTextField descrip;
    JTextField costo;
    JCheckBox refrige;
    JTextField peso;
    JTextField unidad;
    final static String CREAR = "Crear Insumo";
    final static String DESCRIP = "Descripcion";
    final static String COSTO = "Costo por unidad";
    final static String REFRIGE = "Necesita refrigeracion?";
    final static String PESO = "Peso";
    //final static String UNIDAD = "Unidad";

    private PantallaCrearInsumo(){}

    public static void crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCrearInsumo();
        }
        single.descrip = new JTextField(20);
        single.costo = new JTextField(20);
        single.refrige = new JCheckBox();
        single.peso = new JTextField(20);
        single.unidad = new JTextField(20);
        single.agregarPantalla(p);
    }

    private void agregarPantalla(JPanel p) {
        panelGeneral = new JPanel(new MigLayout(" fill, insets 0"));

        JPanel panelDatos = new JPanel (new MigLayout(""));
        panelDatos.setBackground(Color.white);
        JLabel tituloLabel = new JLabel("Crear Insumo");
        JLabel descripcionLabel = new JLabel("Descripcion: ");
        JLabel costoLabel = new JLabel("Costo por Unidad: ");
        JLabel refrigeracionLabel = new JLabel("Necesita refrigeracion? ");
        JLabel pesoLabel = new JLabel("Peso: ");

        tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
        tituloLabel.setForeground(Color.BLUE);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

        panelDatos.add(descripcionLabel,"align label");
        panelDatos.add(descrip, "wrap");
        panelDatos.add(costoLabel,"align label");
        panelDatos.add(costo,"wrap");
        panelDatos.add(refrigeracionLabel,"align label");
        panelDatos.add(refrige, "wrap");
        panelDatos.add(pesoLabel,"align label");
        panelDatos.add(peso,"wrap");
        //panel.add(new JLabel(UNIDAD));
        //panel.add(unidad);

        JButton crear = new JButton("Crear");
        crear.addActionListener(this);
        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);

        panelDatos.add(cancelar,"tag cancelar, span, split 2, right, gaptop 5, sizegroup bttn");
        panelDatos.add(crear,"tag crear, sizegroup bttn");

        panelDatos.setBorder(BorderFactory.createRaisedBevelBorder());

        panelGeneral.add(panelDatos,"span, center, wrap");
        panelGeneral.setBackground(new Color(207,216,220));

        p.add(panelGeneral,"CrearInsumo");

    }

    public void popUpCorrecto(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        JDialog dialog = new JDialog();
        dialog.setSize(400, 200);

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

        dialog.add(panel);

        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        if(button == "Crear") {
            if (!descrip.getText().isEmpty() && !costo.getText().isEmpty() && !peso.getText().isEmpty()) {
                GestorInsumos.getGestor().crear(descrip.getText(), Double.valueOf(costo.getText()), Double.valueOf(peso.getText()), refrige.isSelected());
                popUpCorrecto();
                PantallaInsumo.getSingle().actualizarTablaInsumos();
                //PantallaInsumo.getSingle().actualizarTablaInsumosBuscar(PantallaInsumo.getSingle().panel);
            } else descrip.setText(DESCRIP);
        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Insumos");
        }
    }
}
