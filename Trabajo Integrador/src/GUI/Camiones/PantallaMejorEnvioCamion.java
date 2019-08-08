package GUI.Camiones;

import Dominio.Insumo;
import Dominio.Stock;
import Gestores.GestorCamiones;
import Gestores.GestorPlantas;
import Gestores.GestorStock;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class PantallaMejorEnvioCamion implements ActionListener{

    private static PantallaMejorEnvioCamion single;
    private JPanel panel;
    //private JTable tabla;
    private Integer camioneseleccionado;
    private List<Stock> insumosNecesarios;

    private PantallaMejorEnvioCamion(){}

    public static PantallaMejorEnvioCamion crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaMejorEnvioCamion();
        }
        single.agregarPantalla(p);
        return single;
    }

    /*public static void crearPantallaCrear(JPanel p){

        PantallaMejorEnvioCamion.crearPantalla(p);

    }*/

    public void agregarPantalla(JPanel p){


        panel = new JPanel(new BorderLayout(10,10));

        JLabel tituloLabel = new JLabel("Camiones");
        tituloLabel.setFont(new Font("Roboto",Font.BOLD,32));

        ImageIcon backIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/left-arrow.png").getImage()
                .getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        //funcionamiento de flecha de retroceso
        JButton menu = new JButton(backIcon);
        menu.setBorderPainted(false);
        menu.setBorder(null);
        menu.setFocusable(false);
        menu.setMargin(new Insets(0, 0, 0, 0));
        menu.setContentAreaFilled(false);
        menu.setName("Camiones");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.addActionListener(this);
        menu.setVerticalTextPosition(JButton.TOP);
        menu.setHorizontalTextPosition(JButton.CENTER);

        JButton mejorSeleccionButton = new JButton("Mejor Seleccion");
        mejorSeleccionButton.setName("mejorSeleccion");
        mejorSeleccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
                dialog.setSize(400, 200);

                JLabel preguntaLabel= new JLabel("Combinacion de insumos mas conveniente: ");
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panel.add(preguntaLabel,"center,span, wrap");

                JLabel insumos = new JLabel();
                insumos.setText(String.format("<html><div WIDTH=%d>%s</div></html>",100, GestorCamiones.getGestor().mejorSeleccion(camioneseleccionado,insumosNecesarios)));
                panel.add(insumos,"center,span,wrap");

                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(a -> dialog.dispose());

                panel.add(aceptar,"right, gapright 25");

                dialog.add(panel);

                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.setName("panel datos buscar camiones");
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(mejorSeleccionButton,"tag crear,span,center,gaptop 10,gapbottom 10, sizegroup bttn");

        mejorSeleccionButton.setEnabled(false);

        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        panel.setBackground(new Color(207,216,220));

        /*JPanel panel2 = new JPanel(new MigLayout("fillx","[][grow][]"));
        crearTablaCamiones(GestorCamiones.getGestor().listarCamiones());*/

        panel.add(crearTablaInsumosFaltantes(GestorPlantas.getGestor().listaFaltanteStock()), BorderLayout.CENTER);
        panel.add(crearTablaCamiones(GestorCamiones.getGestor().listarCamiones()),BorderLayout.SOUTH);


        p.add(panel,"MejorEnvio");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getName();
        if(button == "Camiones") {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Camiones");
        }
    }

    public JPanel crearTablaCamiones(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        String columns[]={"Id","Marca","Modelo","Dominio","Año","Costo por Km","Capacidad", "Liquidos"};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class, String.class, String.class, Integer.class, Double.class, Double.class, Boolean.class
        };

        DefaultTableModel model = new DefaultTableModel(data, columns){
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

        JTable tablaCamiones = new JTable(model);
        tablaCamiones.setRowHeight(35);
        tablaCamiones.setGridColor(Color.gray);
        tablaCamiones.setSelectionBackground(new Color(38,198,218));
        tablaCamiones.setShowGrid(true);
        tablaCamiones.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaCamiones.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaCamiones.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaCamiones.setDefaultRenderer(Object.class, centerRenderer);

        tablaCamiones.getColumnModel().getColumn(0).setPreferredWidth(5); // Ancho columna id

        tablaCamiones.getColumnModel().getColumn(4).setPreferredWidth(10); //Ancho columna año

        tablaCamiones.getColumnModel().getColumn(7).setPreferredWidth(10); //Ancho columna año

        tablaCamiones.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaCamiones);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        //tabla=tablaCamiones;
        tablaCamiones.setPreferredSize(new Dimension(400,250));
        tablaCamiones.setPreferredScrollableViewportSize(tablaCamiones.getPreferredSize());
        tablaCamiones.setFillsViewportHeight(true);

        tablaCamiones.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    for (Component component : panel.getParent().getComponents()) {
                        if(component.getName() == "panel datos buscar camiones" ){
                           // System.out.println("Entre en el panel");
                            for (Component component1 : ((JPanel) component).getComponents()) {

                                if(component1 instanceof JButton && ((JButton)component1).getName() == "mejorSeleccion"){
                                    component1.setEnabled(true);

                                }
                                /*if(component1 instanceof JButton && ((JButton)component1).getText( ) == "Editar"){
                                    System.out.println("Boton editar");
                                    component1.setEnabled(true);*/

                                //}
                            }

                        }
                    }
                    camioneseleccionado = (Integer)tablaCamiones.getValueAt(tablaCamiones.getSelectedRow(), 0);
                    System.out.println(tablaCamiones.getValueAt(tablaCamiones.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;

    }

    public JPanel crearTablaInsumosFaltantes(Object[][] data){

        insumosNecesarios = GestorStock.getGestor().insumosFaltantes();

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        //panel.setPreferredSize(new Dimension(20,800));
        //Object[][] data = GestorInsumos.getGestor().listarInsumos();
        String columns[]={"Id-Insumo","Nombre","Cantidad Faltante","Id-Planta","Nombre"};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class, Double.class, Integer.class, String.class
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


        JTable tablaInsumos = new JTable(model);
        tablaInsumos.setRowHeight(35);
        tablaInsumos.setGridColor(Color.gray);
        tablaInsumos.setSelectionBackground(new Color(38,198,218));



        tablaInsumos.setShowGrid(true);
        tablaInsumos.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaInsumos.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaInsumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaInsumos.setDefaultRenderer(Object.class, centerRenderer);

        tablaInsumos.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaInsumos);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //sp.setPreferredSize(new Dimension(20,800));
        tablaInsumos.setPreferredSize(new Dimension(400,200));
        tablaInsumos.setPreferredScrollableViewportSize(tablaInsumos.getPreferredSize());
        tablaInsumos.setFillsViewportHeight(true);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        //tabla=tablaInsumos;

        /*tablaInsumos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                if (tablaInsumos.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println(tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 0).toString());
                }
            }
        });*/


        tablaInsumos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    for (Component component : panel.getParent().getComponents()) {
                        if(component.getName() == "panel datos buscar insumo" ){
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
                    /*insumoSeleccionado = tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 1).toString();
                    System.out.println(tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 0).toString());*/
                }
            }
        });

        return panel;
    }

    public void actualizarTablas(){
        panel.remove(2);
        panel.remove(1);
        panel.add(crearTablaInsumosFaltantes(GestorPlantas.getGestor().listaFaltanteStock()), BorderLayout.CENTER);
        panel.add(crearTablaCamiones(GestorCamiones.getGestor().listarCamiones()),BorderLayout.SOUTH);
        System.out.println("SE ACTUALIZA TABLAS");
    }

    public static PantallaMejorEnvioCamion getInstance(){
        return single;
    }
}
