package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Gestores.GestorStock;
import net.miginfocom.swing.MigLayout;

public class PantallaCrearStock implements ActionListener{
    private static PantallaCrearStock single;
    JPanel panelGeneral;
    JDialog dialog;
    JTextField cantidad;
    JTextField puntoPedido;
    JTextField insumo;
    JTextField planta;
    final static String CREAR = "Crear Stock";
    final static String NOMBRE = "Nombre";
    //final static String UNIDAD = "Unidad";

    private PantallaCrearStock(){}

    public static void crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCrearStock();
        }
        single.dialog = new JDialog();
        single.cantidad = new JTextField(20);
        single.puntoPedido = new JTextField(20);
        single.insumo = new JTextField(20);
        single.planta = new JTextField(20);
        single.agregarPantalla(p);
    }

    private void agregarPantalla(JPanel p) {
        panelGeneral = new JPanel(new MigLayout(" fill, insets 0"));

        JPanel panelDatos = new JPanel (new MigLayout(""));
        panelDatos.setBackground(Color.white);
        JLabel tituloLabel = new JLabel("Crear Stock");
        JLabel cantidadLabel = new JLabel("Cantidad");
        JLabel puntoPedidoLabel = new JLabel("Punto de Pedido:  ");
        JLabel insumoLabel = new JLabel("Id Insumo: ");
        JLabel plantaLabel = new JLabel("Id Planta: ");

        tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
        tituloLabel.setForeground(Color.BLUE);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

        panelDatos.add(cantidadLabel,"align label");
        panelDatos.add(cantidad, "wrap");
        panelDatos.add(puntoPedidoLabel,"align label");
        panelDatos.add(puntoPedido, "wrap");
        panelDatos.add(insumoLabel,"align label");
        panelDatos.add(insumo, "wrap");
        panelDatos.add(plantaLabel,"align label");
        panelDatos.add(planta, "wrap");
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

        p.add(panelGeneral,"CrearStock");

    }

    public void popUpCorrecto(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        this.dialog.setSize(400, 200);

        JLabel preguntaLabel= new JLabel("Stock creado correctamente");
        preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
        panel.add(preguntaLabel,"center,span, wrap");


        JButton aceptar = new JButton("Crear otro stock");

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        JButton cancelar = new JButton("Volver a Stock");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = (JPanel)panelGeneral.getParent();
                CardLayout pane = (CardLayout)(p.getLayout());
                pane.show(p, "Stock");
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

    public void popUpIncorrecto(){
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        this.dialog.setSize(400, 200);

        JLabel preguntaLabel= new JLabel("Stock no creado");
        preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
        panel.add(preguntaLabel,"center,span, wrap");


        JButton aceptar = new JButton("Crear otro stock");

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        JButton cancelar = new JButton("Volver a Stock");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = (JPanel)panelGeneral.getParent();
                CardLayout pane = (CardLayout)(p.getLayout());
                pane.show(p, "Stock");
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
            if (!cantidad.getText().isEmpty() && !puntoPedido.getText().isEmpty() && !insumo.getText().isEmpty() && !planta.getText().isEmpty()) {
                if(GestorStock.getGestor().crear(Double.valueOf(cantidad.getText()), Integer.valueOf(puntoPedido.getText()), Integer.valueOf(insumo.getText()), Integer.valueOf(planta.getText())))	popUpCorrecto();
                else	popUpIncorrecto();
                PantallaStock.getSingle().actualizarTablaStock();
            }
        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panelGeneral.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Stock");
        }
    }
}
