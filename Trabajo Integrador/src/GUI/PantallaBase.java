package GUI;

import Dominio.Camino;
import Dominio.Insumo;
import Dominio.Planta;
import GUI.Caminos.PantallaCaminos;
import GUI.Caminos.PantallaCrearCamino;
import GUI.Camiones.PantallaCamiones;
import GUI.Camiones.PantallaCrearCamion;
import GUI.Camiones.PantallaMejorEnvioCamion;
import GUI.Insumos.PantallaInsumo;
import GUI.Plantas.PantallaCrearPlanta;
import GUI.Plantas.PantallaMapaPlanta;
import GUI.Plantas.PantallaPlantas;
import GUI.Stock.PantallaCrearStock;
import GUI.Stock.PantallaStock;
import Gestores.GestorCaminos;
import Gestores.GestorCamiones;
import Gestores.GestorPlantas;

import java.awt.*;
import javax.swing.*;

public class PantallaBase{
    private static JPanel panel;
    PantallaMenu pantallaMenu;
    PantallaInsumo pantallaInsumos;
    PantallaPlantas pantallaPlantas;
    PantallaStock pantallaStock;
    PantallaCamiones pantallaCamiones;

    public static JPanel getPanel() {
        return panel;
    }

    public void agregarComponentePane(Container pane) {
        panel = new JPanel(new CardLayout());
        
        PantallaMenu.crearPantalla(panel);
        PantallaInsumo.crearPantalla(panel);
        PantallaPlantas.crearPantalla(panel);
        PantallaStock.crearPantalla(panel);
        PantallaCaminos.crearPantalla(panel);
        PantallaCamiones.crearPantalla(panel);
        PantallaInformacion.crearPantalla(panel);

        PantallaInsumo.crearPantallaBuscar(panel);
        PantallaInsumo.crearPantallaCrear(panel);

        PantallaCrearPlanta.crearPantalla(panel);
        PantallaMapaPlanta.crearPantalla(panel);
        
        PantallaCrearStock.crearPantalla(panel);

        PantallaCrearCamino.crearPantalla(panel);

        PantallaCrearCamion.crearPantalla(panel);
        PantallaMejorEnvioCamion.crearPantalla(panel);

        

        pane.add(panel, BorderLayout.CENTER);
    }
    private static void crearYmostrarUI() {
        JFrame frame = new JFrame("Trabajo Practico DIED");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1080,720));
        frame.setMinimumSize(new Dimension(600, 400));


        PantallaBase demo = new PantallaBase();
        demo.agregarComponentePane(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

   /* public static void main(String[] args) {
        PantallaBase.crearYmostrarUI();
    }*/
    public static void main(String[] args) {

        //for(int i=1;i<16;i++){

        Insumo insumo1 = new Insumo("A",10.0,2.5,false);
        Insumo insumo2 = new Insumo("B",20.0,2.5,false);
        Insumo insumo3 = new Insumo("C",30.0,2.5,true);
        Insumo insumo4 = new Insumo("D",40.0,2.5,false);
        Insumo insumo5 = new Insumo("F",50.0,2.5,true);
        Insumo insumo6 = new Insumo("G",60.0,2.5,false);
        Insumo insumo7 = new Insumo("H",70.0,2.5,true);
        Insumo insumo8 = new Insumo("I",80.0,2.5,false);
        Insumo insumo9 = new Insumo("j",90.0,2.5,false);

        //Planta planta1 = new Planta("Planta Acopio Puerto");
        Planta planta2 = new Planta("P2");
        Planta planta3 = new Planta("P3");
        Planta planta4 = new Planta("P4");
        Planta planta5 = new Planta("P5");
        Planta planta6 = new Planta("P6");
        Planta planta7 = new Planta("P7");
        //Planta planta8 = new Planta("Planta Acopio Final");

        GestorCaminos.getGestor().crear(0,1,200.0,1500,13000.0);

        GestorCaminos.getGestor().crear(0,2,150.0,180,15000.0);

        GestorCaminos.getGestor().crear(1,3,100.0,70,10000.0);

        GestorCaminos.getGestor().crear(1,4,80.0,40,10000.0);

        GestorCaminos.getGestor().crear(2,5,250.0,180,10000.0);

        GestorCaminos.getGestor().crear(5,6,400.0,350,5000.0);

        GestorCaminos.getGestor().crear(6,4,320.,300,20000.0);

        GestorCaminos.getGestor().crear(5,7,100.0,70,6000.0);

        GestorCaminos.getGestor().crear(3,7,200.0,150,7000.0);
        
        GestorCaminos.getGestor().crear(4,3,200.0,150,7000.0);

        GestorCamiones.getGestor().crear("VW","v40","fjt133",2018,100.0,10000,false);
        GestorCamiones.getGestor().crear("Iveco","Flat","RPE537",2018,120.0,10000,true);
        GestorCamiones.getGestor().crear("Scania","ST","MLB995",2019,200.0,15000,false);


        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PantallaBase.crearYmostrarUI();
            }
        });
    }
}