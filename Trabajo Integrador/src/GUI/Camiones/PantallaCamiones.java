package GUI.Camiones;

import Gestores.GestorCamiones;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import GUI.PantallaBase;

public class PantallaCamiones implements ActionListener{
    private static PantallaCamiones single;
    JPanel panel;
    JTable tabla;
    Integer camioneseleccionado;
    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaCamiones(){}

    public static PantallaCamiones crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCamiones();
        }
        single.agregarPantalla(p);
        return single;
    }

    public static void crearPantallaCrear(JPanel p){

        PantallaCrearCamion.crearPantalla(p);

    }

    public static PantallaCamiones getSingle() {
        return single;
    }

    public void agregarPantalla(JPanel p) {
        /*panel = new JPanel();
        JButton menu1 = new JButton(MENU);
        menu1.addActionListener(this);

        panel.add(menu1);
        panel.add(new JButton(CREAR));
        panel.add(new JButton(BUSCAR));
        panel.add(new JButton(EDITAR));
        panel.add(new JButton(BORRAR));
        */

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
        menu.setName("Menu");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.addActionListener(this);
        menu.setVerticalTextPosition(JButton.TOP);
        menu.setHorizontalTextPosition(JButton.CENTER);

        JButton crear = new JButton(CREAR);
        crear.setName("Crear");
        crear.addActionListener(this);

        JButton mejorSeleccionButton = new JButton("Mejor Seleccion");
        mejorSeleccionButton.setName("mejorSeleccion");
        mejorSeleccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
                dialog.setSize(400, 200);

                JLabel preguntaLabel= new JLabel("Camion creado correctamente");
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panel.add(preguntaLabel,"center,span, wrap");


                JButton aceptar = new JButton("Crear otro camion");

                aceptar.addActionListener(a -> dialog.dispose());

                JButton cancelar = new JButton("Volver a Camiones");
                cancelar.addActionListener(a -> {
                    JPanel p = (JPanel)panel.getParent();
                    CardLayout pane = (CardLayout)(p.getLayout());
                    pane.show(p, "Camiones");
                    dialog.setVisible(false);
                    //dialog.dispose();
                });

                panel.add(cancelar,"left, gapleft 25");
                panel.add(aceptar,"right, gapright 25");

                dialog.add(panel);

                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 3,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(mejorSeleccionButton);

        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        panel.setBackground(new Color(207,216,220));

        JPanel panel2 = new JPanel(new MigLayout("fillx","[][grow][]"));
        crearTablaCamiones(GestorCamiones.getGestor().listarCamiones());

        panel.add(crearTablaCamiones(GestorCamiones.getGestor().listarCamiones()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));
        panel.setBackground(new Color(207,216,220));
        
        
        p.add(panel, "Camiones");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getName();
        if(button == MENU) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Menu");
        }
        else if(button == CREAR) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "CrearCamion");
            //Operaciones de crear, buscar, editar y borrar.

        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Camiones");
            tabla.clearSelection();
            actualizarTablaCamiones();

        }
    }


    public JPanel crearTablaCamiones(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        String columns[]={"Id","Costo por Km","Capacidad", "Liquido"};

        final Class[] columnClass = new Class[] {
                Integer.class, Double.class, Double.class, Double.class
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

        tablaCamiones.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaCamiones);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaCamiones;
        
        tablaCamiones.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    /*for (Component component : panel.getParent().getComponents()) {
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
                    }*/
                    camioneseleccionado = (Integer)tablaCamiones.getValueAt(tablaCamiones.getSelectedRow(), 0);
                    System.out.println(tablaCamiones.getValueAt(tablaCamiones.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;
        
    }

    public void actualizarTablaCamiones(){

        panel.remove(1);
        panel.add(this.crearTablaCamiones(GestorCamiones.getGestor().listarCamiones()));
        panel.revalidate();

    }

}
