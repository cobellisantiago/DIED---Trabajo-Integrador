
package GUI.Stock;

import Gestores.GestorPlantas;
import Gestores.GestorStock;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dominio.Planta;
import Dominio.Stock;

public class PantallaStock implements ActionListener{


    private static PantallaStock single;
    JPanel panel;
    JTable tabla;

    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaStock(){}

    public static PantallaStock crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaStock();
            single.agregarPantalla(p);
        }
        return single;
    }

    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        /*panel = new JPanel();
        JButton menu1 = new JButton(MENU);
        menu1.addActionListener(this);
        panel.add(menu1);
        panel.add(new JButton(CREAR));
        panel.add(new JButton(BUSCAR));
        panel.add(new JButton(EDITAR));
        panel.add(new JButton(BORRAR));*/

        panel = new JPanel(new BorderLayout(10,10));

        JLabel tituloLabel = new JLabel("Stock");
        tituloLabel.setFont(new Font("Roboto",Font.BOLD,32));


        ImageIcon backIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/left-arrow.png").getImage()
                .getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        JButton menu = new JButton(backIcon);
        menu.setBorderPainted(false);
        menu.setBorder(null);
        menu.setFocusable(false);
        menu.setMargin(new Insets(0, 0, 0, 0));
        menu.setContentAreaFilled(false);
        menu.setName("Menu");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.addActionListener(this);
        menu.setVerticalTextPosition(JButton.TOP);
        menu.setHorizontalTextPosition(JButton.CENTER);

        JButton crear = new JButton(CREAR);
        crear.setName("Crear");
        crear.addActionListener(this);

        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelDialog = new JPanel(new MigLayout("fill, insets 0","[][]"));
                JDialog dialog = new JDialog();
                String stockSeleccionado = tabla.getValueAt(tabla.getSelectedRow(), 0).toString() + " - " + tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                dialog.setSize(400, 200);
                JLabel preguntaLabel= new JLabel("¿Seguro que desea eliminar el");
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(preguntaLabel,"span,center,wrap");
                JLabel stockLabel= new JLabel("stock "+stockSeleccionado+" ?");
                stockLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(stockLabel,"span,center,wrap,gaptop 0");



                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorStock.getGestor().eliminiar((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0), (Integer)tabla.getValueAt(tabla.getSelectedRow(), 1));
                        actualizarTablaStock();
                        dialog.dispose();
                        editar.setEnabled(false);
                        eliminar.setEnabled(false);
                    }
                });

                JButton cancelar = new JButton("Cancelar");
                cancelar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        editar.setEnabled(false);
                        eliminar.setEnabled(false);
                    }
                });

                panelDialog.add(cancelar,"left, gapleft 25");//"tag cancelar, sizegroup bttn");
                panelDialog.add(aceptar,"right, gapright 25");//"tag aceptar, sizegroup bttn");

                dialog.add(panelDialog);

                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        editar.addActionListener(new ActionListener() {
            @SuppressWarnings("Duplicates")
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelDialog = new JPanel(new MigLayout("fill, insets 0","[][]"));
                JDialog dialog = new JDialog();
                String stockSeleccionado = tabla.getValueAt(tabla.getSelectedRow(), 0).toString() + " - " + tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                panelDialog.setBackground(new Color(207,216,220));
                dialog.setSize(500, 300);
                JPanel panelDatos = new JPanel (new MigLayout(""));
                //panelDatos.setBackground(Color.white);
                JLabel tituloLabel = new JLabel("Editar Stock: "+stockSeleccionado);
                JLabel cantidadLabel = new JLabel("Cantidad: ");
                JLabel puntoPedidoLabel = new JLabel("Punto Pedido: ");
                tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
                tituloLabel.setForeground(Color.BLUE);
                tituloLabel.setHorizontalAlignment(JLabel.CENTER);
                panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

                JTextField cantidad = new JTextField(30);
                JTextField puntoPedido = new JTextField(30);

                panelDatos.add(cantidadLabel,"align label");
                panelDatos.add(cantidad, "wrap");
                panelDatos.add(puntoPedidoLabel,"align label");
                panelDatos.add(puntoPedido, "wrap");

                Stock plantaAEditar = GestorStock.getGestor().getStock((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0), (Integer)tabla.getValueAt(tabla.getSelectedRow(), 1));
                cantidad.setText(plantaAEditar.getCantidad().toString());
                puntoPedido.setText(plantaAEditar.getPuntoPedido().toString());

                panelDialog.add(panelDatos,"center, span");

                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorStock.getGestor().editar((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0),(Integer)tabla.getValueAt(tabla.getSelectedRow(), 1), Double.valueOf(cantidad.getText()), Integer.valueOf(puntoPedido.getText()));
                        actualizarTablaStock();
                        dialog.dispose();
                        editar.setEnabled(false);
                        eliminar.setEnabled(false);
                    }
                });

                JButton cancelar = new JButton("Cancelar");
                cancelar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        editar.setEnabled(false);
                        eliminar.setEnabled(false);
                    }
                });

                panelDialog.add(cancelar,"left, gapleft 25");//"tag cancelar, sizegroup bttn");
                panelDialog.add(aceptar,"right, gapright 25");//"tag aceptar, sizegroup bttn");

                dialog.add(panelDialog);

                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        editar.setEnabled(false);
        eliminar.setEnabled(false);

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.setName("panel datos buscar stock");
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 3,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(editar,"tag buscar, sizegroup bttn");
        panelBotones.add(eliminar,"tag buscar, sizegroup bttn");

        //panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        panel.add(crearTablaStock(GestorStock.getGestor().listarStock()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));
        panel.setBackground(new Color(207,216,220));


        p.add(panel, "Stock");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getName();
        if(button == MENU) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);

        } else if(button == CREAR) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "CrearStock");
            //Operaciones de crear, buscar, editar y borrar.
        }else{

        }
    }

    public JPanel crearTablaStock(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        //Object[][] data = GestorStock.getGestor().listarStock();
        String columns[]={"Id Planta","Id Insumo","Cantidad","Punto de Pedido"};

        final Class[] columnClass = new Class[] {
                Integer.class, Integer.class, Double.class, Integer.class
        };
        //create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }


        };


        JTable tablaStock = new JTable(model);
        tablaStock.setRowHeight(35);
        tablaStock.setGridColor(Color.gray);
        tablaStock.setSelectionBackground(new Color(38,198,218));


        tablaStock.setShowGrid(true);
        tablaStock.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaStock.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaStock.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaStock.setDefaultRenderer(Object.class, centerRenderer);

        tablaStock.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaStock);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaStock;

        /*tablaStock.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tablaStock.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println(tablaStock.getValueAt(tablaStock.getSelectedRow(), 0).toString());
                }
            }
        });*/

        tablaStock.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    for (Component component : panel.getParent().getComponents()) {
                        if(component.getName() == "panel datos buscar stock" ){
                            for (Component component1 : ((JPanel) component).getComponents()) {
                                if(component1 instanceof JButton && ((JButton)component1).getText() == "Eliminar"){
                                    component1.setEnabled(true);

                                }
                                if(component1 instanceof JButton && ((JButton)component1).getText( ) == "Editar"){
                                    System.out.println("Boton editar");
                                    component1.setEnabled(true);

                                }
                            }

                        }
                    }
                    System.out.println(tablaStock.getValueAt(tablaStock.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;
    }

    public void actualizarTablaStock() {

        panel.remove(1);
        panel.add(this.crearTablaStock(GestorStock.getGestor().listarStock()));
        panel.revalidate();

    }

    private void agregarGrafo(){
        //if(this.panelGrafo == null) this.panelGrafo = new PanelGrafoStock();

        //panel.add(new PanelGrafoStock());
    }

    public static PantallaStock getSingle() {
        return single;
    }


}

