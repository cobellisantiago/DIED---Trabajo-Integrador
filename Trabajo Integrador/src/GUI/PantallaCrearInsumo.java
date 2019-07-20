package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Dominio.Unidades.Unidad;
import Dominio.Unidades.UnidadDeMasa;
import Gestores.GestorInsumos;

public class PantallaCrearInsumo implements ActionListener{
    private static PantallaCrearInsumo single;
    JDialog dialog;
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

    public static void crearPantalla(){
        if(single == null) {
            single = new PantallaCrearInsumo();
        }
        single.dialog = new JDialog();
        single.descrip = new JTextField(20);
        single.costo = new JTextField(20);
        single.refrige = new JCheckBox();
        single.peso = new JTextField(20);
        single.unidad = new JTextField(20);
        single.crearDialog();
    }

    private void crearDialog() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

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
        this.dialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
    	if(!descrip.getText().isEmpty() && !costo.getText().isEmpty() && !peso.getText().isEmpty()) {
    		GestorInsumos.getGestor().crear(descrip.getText(), Double.valueOf(costo.getText()), Double.valueOf(peso.getText()), refrige.isSelected());
    		dialog.dispose();
    	}
    	else	descrip.setText(DESCRIP);
    }
}