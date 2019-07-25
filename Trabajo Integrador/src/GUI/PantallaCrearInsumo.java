package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Dominio.Unidades.Unidad;
import Dominio.Unidades.UnidadDeMasa;
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
        single.panelGeneral = new JPanel(new MigLayout(" fill, insets 0"));
        single.descrip = new JTextField(20);
        single.costo = new JTextField(20);
        single.refrige = new JCheckBox();
        single.peso = new JTextField(20);
        single.unidad = new JTextField(20);
        single.agregarPantalla(p);
    }

    private void agregarPantalla(JPanel p) {

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
        /*JPanel panel = new JPanel(new GridLayout(5, 2));

        this.dialog.setSize(400, 200);

        panel.add(new JLabel(DESCRIP));
        panel.add(descrip);
        panel.add(new JLabel(COSTO));
        panel.add(costo);
        panel.add(new JLabel(REFRIGE));
        panel.add(refrige);
        panel.add(new JLabel(PESO));
        panel.add(peso);
        //panel.add(new JLabel(UNIDAD));
        //panel.add(unidad);

        JButton crear = new JButton(CREAR);
        crear.addActionListener(this);
        
        panel.add(crear);

        this.dialog.add(panel);

        this.dialog.setModal(true);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setVisible(true);*/

        p.add(panelGeneral,"CrearInsumo");

    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        if(button == "Crear") {
            if (!descrip.getText().isEmpty() && !costo.getText().isEmpty() && !peso.getText().isEmpty()) {
                GestorInsumos.getGestor().crear(descrip.getText(), Double.valueOf(costo.getText()), Double.valueOf(peso.getText()), refrige.isSelected());
                JPanel p = (JPanel)panelGeneral.getParent();
                CardLayout pane = (CardLayout)(p.getLayout());
                
                JPanel panel = (JPanel)p.getComponent(1);
                panel.remove(1);
                panel.add(PantallaInsumo.crearPantalla(panel).crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()));
                panel.revalidate();
                
                pane.show(p, "Insumos");
            }
        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Insumos");
        }
        
    }
}