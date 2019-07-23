package GUI;

import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class PantallaMenu implements ActionListener{

    private static PantallaMenu single;
    JPanel panel;
    final static String INSUMOS = "Insumos";
    final static String PLANTAS = "Plantas";
    final static String STOCK = "Stock";
    final static String CAMINOS = "Caminos";
    final static String CAMIONES = "Camiones";
    final static String INFORMACION = "Informacion";

    private PantallaMenu(){}

    public static PantallaMenu crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaMenu();
            single.agregarPantalla(p);
        }
        return single;
    }

    public void agregarPantalla(JPanel p) {

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));


        ImageIcon insumoIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/flour.png").getImage()
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ImageIcon plantaIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/factory.png").getImage()
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ImageIcon stockIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/stock.png").getImage()
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ImageIcon caminosIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/route.png").getImage()
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ImageIcon camionesIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/truck.png").getImage()
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ImageIcon informacionIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/information.png").getImage()
                .getScaledInstance(60, 60, Image.SCALE_DEFAULT));

        JButton insumosButton = new JButton(INSUMOS,insumoIcon);
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

        Dimension dimensionBotonMenuPrincipal = new Dimension(150,150);

        insumosButton.setPreferredSize(dimensionBotonMenuPrincipal);
        plantasButton.setPreferredSize(dimensionBotonMenuPrincipal);
        stockButton.setPreferredSize(dimensionBotonMenuPrincipal);
        caminosButton.setPreferredSize(dimensionBotonMenuPrincipal);
        camionesButton.setPreferredSize(dimensionBotonMenuPrincipal);
        informacionButton.setPreferredSize(dimensionBotonMenuPrincipal);

        Color colorBoton = new Color(3,155,229);
        LineBorder bordeBoton = new LineBorder(Color.black,2);

        /*insumosButton.setBackground(colorBoton);
        //insumosButton.setBorder(bordeBoton);
        plantasButton.setBackground(colorBoton);
        //plantasButton.setBorder(bordeBoton);
        stockButton.setBackground(colorBoton);
        //stockButton.setBorder(bordeBoton);
        caminosButton.setBackground(colorBoton);
        //caminosButton.setBorder(bordeBoton);
        camionesButton.setBackground(colorBoton);
        //camionesButton.setBorder(bordeBoton);
        informacionButton.setBackground(colorBoton);
        //informacionButton.setBorder(bordeBoton);*/

        Font fuenteBotones = new Font("Roboto",Font.BOLD,20);

        insumosButton.setFont(fuenteBotones);
        plantasButton.setFont(fuenteBotones);
        stockButton.setFont(fuenteBotones);
        caminosButton.setFont(fuenteBotones);
        camionesButton.setFont(fuenteBotones);
        informacionButton.setFont(fuenteBotones);

        insumosButton.addActionListener(this);
        plantasButton.addActionListener(this);
        stockButton.addActionListener(this);
        caminosButton.addActionListener(this);
        camionesButton.addActionListener(this);
        informacionButton.addActionListener(this);

        panel.add(insumosButton);
        panel.add(plantasButton);
        panel.add(stockButton);
        panel.add(caminosButton);
        panel.add(camionesButton);
        panel.add(informacionButton);

        //panel.setBackground(new Color(3,155,229));
        //panel.setBackground(new Color(255,255,255));
        panel.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panel.setPreferredSize(new Dimension(540,350));
        panel.setBackground(new Color(207,216,220));
        /*JPanel infoPanel = new JPanel();
        //infoPanel.setPreferredSize(new Dimension(1080,720));
        infoPanel.setBackground(new Color(0,109,179));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Panel de Informacion"));
        panel.add(infoPanel,BorderLayout.SOUTH);*/

        p.add(panel, "Menu");




    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getText();

        JPanel p = (JPanel)panel.getParent();
        CardLayout pane = (CardLayout)(p.getLayout());
        pane.show(p, button);
    }
}
