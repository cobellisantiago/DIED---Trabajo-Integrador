import sun.java2d.loops.FontInfo;

import javax.swing.*;
import java.awt.*;

public class APP {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI(){

        JFrame framePrincipal = new JFrame("Gestion de Plantas");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // framePrincipal.setTitle("Gestión de Plantas");
        framePrincipal.setPreferredSize(new Dimension(1080,720));
        framePrincipal.setMinimumSize(new Dimension(600, 400));


        //Creamos la barra de herramientas
        JMenuBar menuBar;
        JMenu menu1, menu2, menu3;
        JMenuItem item1Menu1, item2Menu1, item1Menu2;
        JPanel panelPrincipal,panelSuperior,panelCentral,panelInferior;
        JButton boton1, boton2, boton3, boton4;


        panelPrincipal = new JPanel();
        panelInferior = new JPanel();
        panelCentral = new JPanel();
        panelSuperior = new JPanel();

        framePrincipal.getContentPane().add(panelPrincipal);

        GridLayout gridPrincipal = new GridLayout(3,5);

        panelPrincipal.setLayout(gridPrincipal);

        panelPrincipal.add(panelSuperior);
        panelPrincipal.add(panelCentral);
        panelPrincipal.add(panelInferior);
        panelCentral.setBackground(Color.white);
        //panelCentral.setLayout(new GridLayout(1,5));

        boton1 = new JButton("Plantas");
        boton2 = new JButton("Camiones");
        boton3 = new JButton("Insumos");
        boton4 = new JButton("Rutas" );

        boton1.setBounds(50,60,170,100);
        boton2.setBounds(310,60,170,100);
        boton3.setBounds(580,60,170,100);
        boton4.setBounds(850,60,170,100);

        boton1.setFont(new Font("Roboto",Font.BOLD,20));
        boton1.setBackground(Color.green.darker());
        boton1.setOpaque(true);
        boton2.setFont(new Font("Roboto",Font.BOLD,20));
        boton2.setBackground(Color.green.darker());

        boton3.setFont(new Font("Roboto",Font.BOLD,20));
        boton3.setBackground(Color.lightGray);

        boton4.setFont(new Font("Roboto",Font.BOLD,20));
        boton4.setBackground(Color.lightGray);

        panelCentral.setLayout(null);

        panelCentral.add(boton1);
        panelCentral.add(boton2);
        panelCentral.add(boton3);
        panelCentral.add(boton4);







        framePrincipal.pack();
        framePrincipal.setVisible(true);
    }

}

