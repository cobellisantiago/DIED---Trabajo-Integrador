package GUI;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class PantallaMenuPrueba implements ActionListener{
    private static PantallaMenuPrueba single;
    JPanel panel;
    final static String INSUMOS = "Insumos";
    final static String PLANTAS = "Plantas";
    final static String STOCK = "Stock de Insumos";
    final static String CAMINOS = "Caminos";
    final static String CAMIONES = "Camiones";
    final static String INFORMACION = "Informacion";

    private PantallaMenuPrueba(){}

    public static PantallaMenuPrueba crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaMenuPrueba();
            single.agregarPantalla(p);
        }
        return single;
    }


    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        panel = new JPanel();

        ImageIcon insumoIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/flour.png").getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ImageIcon plantaIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/factory.png").getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ImageIcon stockIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/stock.png").getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ImageIcon caminosIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/route.png").getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ImageIcon camionesIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/truck.png").getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ImageIcon informacionIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/information.png").getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));

        JButton insumosButton = new JButton(INSUMOS,insumoIcon);
        insumosButton.addActionListener(this);

        JButton plantasButton = new JButton(PLANTAS,plantaIcon);
        JButton stockButton = new JButton(STOCK,stockIcon);
        JButton caminosButton = new JButton(CAMINOS,caminosIcon);
        JButton camionesButton = new JButton(CAMIONES,camionesIcon);
        JButton informacionButton = new JButton(INFORMACION,informacionIcon);

        insumosButton.setVerticalTextPosition(JButton.TOP);
        insumosButton.setHorizontalTextPosition(JButton.CENTER);
        plantasButton.setVerticalTextPosition(JButton.TOP);
        plantasButton.setHorizontalTextPosition(JButton.CENTER);
        stockButton.setVerticalTextPosition(JButton.TOP);
        stockButton.setHorizontalTextPosition(JButton.CENTER);
        caminosButton.setVerticalTextPosition(JButton.TOP);
        caminosButton.setHorizontalTextPosition(JButton.CENTER);
        camionesButton.setVerticalTextPosition(JButton.TOP);
        camionesButton.setHorizontalTextPosition(JButton.CENTER);
        informacionButton.setVerticalTextPosition(JButton.TOP);
        informacionButton.setHorizontalTextPosition(JButton.CENTER);

        Dimension dimensionBotonMenuPrincipal = new Dimension(150,120);

        insumosButton.setPreferredSize(dimensionBotonMenuPrincipal);
        plantasButton.setPreferredSize(dimensionBotonMenuPrincipal);
        stockButton.setPreferredSize(dimensionBotonMenuPrincipal);
        caminosButton.setPreferredSize(dimensionBotonMenuPrincipal);
        camionesButton.setPreferredSize(dimensionBotonMenuPrincipal);
        informacionButton.setPreferredSize(dimensionBotonMenuPrincipal);

        panel.add(insumosButton);
        panel.add(plantasButton);
        panel.add(stockButton);
        panel.add(caminosButton);
        panel.add(camionesButton);
        panel.add(informacionButton);

        panel.setBackground(new Color(255,255,255));



        p.add(panel, "Menu");

    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();
        JPanel p = (JPanel)panel.getParent();

        CardLayout pane = (CardLayout)(p.getLayout());
        pane.show(p, button);
    }
}
