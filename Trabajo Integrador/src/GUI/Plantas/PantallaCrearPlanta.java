package GUI.Plantas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Gestores.GestorPlantas;
import net.miginfocom.swing.MigLayout;

public class PantallaCrearPlanta implements ActionListener{
    private static PantallaCrearPlanta single;
    JPanel panelGeneral;
    JDialog dialog;
    JTextField nombre;
    final static String CREAR = "Crear Planta";
    final static String NOMBRE = "Nombre";
    //final static String UNIDAD = "Unidad";

    private PantallaCrearPlanta(){}

    public static void crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCrearPlanta();
        }
        single.dialog = new JDialog();
        single.nombre = new JTextField(20);
        single.agregarPantalla(p);
    }

    private void agregarPantalla(JPanel p) {
        panelGeneral = new JPanel(new MigLayout(" fill, insets 0"));

        JPanel panelDatos = new JPanel (new MigLayout(""));
        panelDatos.setBackground(Color.white);
        JLabel tituloLabel = new JLabel("Crear Planta");
        JLabel nombreLabel = new JLabel("Nombre: ");

        tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
        tituloLabel.setForeground(Color.BLUE);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

        panelDatos.add(nombreLabel,"align label");
        panelDatos.add(nombre, "wrap");
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

        p.add(panelGeneral,"CrearPlanta");

    }

    public void popUpCorrecto(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        this.dialog.setSize(400, 200);

        JLabel preguntaLabel= new JLabel("Planta creada correctamente");
        preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
        panel.add(preguntaLabel,"center,span, wrap");


        JButton aceptar = new JButton("Crear otra planta");

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        JButton cancelar = new JButton("Volver a Plantas");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = (JPanel)panelGeneral.getParent();
                CardLayout pane = (CardLayout)(p.getLayout());
                pane.show(p, "Plantas");
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
            if (!nombre.getText().isEmpty()) {
                GestorPlantas.getGestor().crear(nombre.getText());
                popUpCorrecto();
                PantallaPlantas.getSingle().actualizarTablaPlantas();
            }
        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Plantas");
        }
    }
}
