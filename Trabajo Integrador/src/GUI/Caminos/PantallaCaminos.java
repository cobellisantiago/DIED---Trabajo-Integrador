package GUI.Caminos;

import Gestores.GestorCaminos;
import Gestores.GestorPlantas;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dominio.Camino;
import Dominio.Planta;

public class PantallaCaminos implements ActionListener{
    private static PantallaCaminos single;
    private JPanel panel;
    private JTable tabla;

    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaCaminos(){}

    public static PantallaCaminos crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaCaminos();
            single.agregarPantalla(p);
        }
        return single;
    }

    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        panel = new JPanel(new BorderLayout(10,10));

        JLabel tituloLabel = new JLabel("Caminos");
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
        crear.setName("CrearCamino");
        crear.addActionListener(this);

        JButton mapaButton = new JButton("Mapa");
        mapaButton.setName("Mapa");
        mapaButton.addActionListener(this);

        JButton flujoButton = new JButton("Flujo Maximo");
        flujoButton.setName("FlujoMaximo");
        flujoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
                JDialog dialog = new JDialog();
                dialog.setSize(400, 200);

               Double valor = GestorCaminos.getGestor().obtenerFlujoMaximo();

                JLabel preguntaLabel= new JLabel("El flujo maximo es: "+valor);
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panel.add(preguntaLabel,"center,span, wrap");

                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                panel.add(aceptar,"center");

                dialog.add(panel);

                dialog.setModal(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelDialog = new JPanel(new MigLayout("fill, insets 0","[][]"));
                JDialog dialog = new JDialog();
                String caminoSeleccionado = tabla.getValueAt(tabla.getSelectedRow(), 0).toString() + " - " + tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                dialog.setSize(400, 200);
                JLabel preguntaLabel= new JLabel("�Seguro que desea eliminar el");
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(preguntaLabel,"span,center,wrap");
                JLabel caminoLabel= new JLabel("camino "+caminoSeleccionado+" ?");
                caminoLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(caminoLabel,"span,center,wrap,gaptop 0");



                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorCaminos.getGestor().eliminiar((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0), (Integer)tabla.getValueAt(tabla.getSelectedRow(), 1));
                        actualizarTablaCaminos();
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
                String caminoSeleccionado = tabla.getValueAt(tabla.getSelectedRow(), 0).toString() + " - " + tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                panelDialog.setBackground(new Color(207,216,220));
                dialog.setSize(500, 300);
                JPanel panelDatos = new JPanel (new MigLayout(""));
                //panelDatos.setBackground(Color.white);
                JLabel tituloLabel = new JLabel("Editar Camino: "+caminoSeleccionado);
                JLabel distLabel = new JLabel("Distancia: ");
                JLabel durLabel = new JLabel("Duracion: ");
                JLabel pesoMLabel = new JLabel("Peso Maximo: ");
                tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
                tituloLabel.setForeground(Color.BLUE);
                tituloLabel.setHorizontalAlignment(JLabel.CENTER);
                panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

                JTextField dist = new JTextField(30);
                JTextField dur = new JTextField(30);
                JTextField pesoM = new JTextField(30);

                panelDatos.add(distLabel,"align label");
                panelDatos.add(dist, "wrap");
                panelDatos.add(durLabel,"align label");
                panelDatos.add(dur, "wrap");
                panelDatos.add(pesoMLabel,"align label");
                panelDatos.add(pesoM, "wrap");

                Camino caminoAEditar = GestorCaminos.getGestor().getCamino(GestorPlantas.getGestor().getPlanta((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0)), GestorPlantas.getGestor().getPlanta((Integer)tabla.getValueAt(tabla.getSelectedRow(), 1)));
                dist.setText(caminoAEditar.getDistancia().toString());
                dur.setText(caminoAEditar.getDuracion().toString());
                pesoM.setText(caminoAEditar.getPesoMaximo().toString());

                panelDialog.add(panelDatos,"center, span");

                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorCaminos.getGestor().editar((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0), (Integer)tabla.getValueAt(tabla.getSelectedRow(), 1), Double.valueOf(dist.getText()), Integer.valueOf(dur.getText()), Double.valueOf(pesoM.getText()));
                        actualizarTablaCaminos();
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
        panelBotones.setName("panel datos buscar caminos");
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 5,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(editar,"tag buscar, sizegroup bttn");
        panelBotones.add(eliminar,"tag buscar, sizegroup bttn");
        panelBotones.add(mapaButton,"tag mapa, sizegroup bttn");
        panelBotones.add(flujoButton,"tag mapa, sizegroup bttn");


        //panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);
        
        panel.add(crearTablaCaminos(GestorCaminos.getGestor().listarCaminos()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));
        panel.setBackground(new Color(207,216,220));
        
        //panel.add(crearTablaPlantas(GestorPlantas.getGestor().listarPlantas()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));

        p.add(panel, "Caminos");
    }


    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getName();
        if(button == "Menu") {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);
        }
        else if(button == "CrearCamino") {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, button);
            PantallaCrearCamino.getSingle().actualizarDatos();
            //Operaciones de crear, buscar, editar y borrar.

        }else if(button == "Mapa"){
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "MapaPlanta");
        }
    }
    
    public JPanel crearTablaCaminos(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        //Object[][] data = GestorPlantas.getGestor().listarPlantas();
        String columns[]={"Id Planta Origen","Id Planta Destino","Distancia","Duracion","Peso Maximo"};

        final Class[] columnClass = new Class[] {
                Integer.class, Integer.class, Double.class, Integer.class, Double.class
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


        JTable tablaCaminos = new JTable(model);
        tablaCaminos.setRowHeight(35);
        tablaCaminos.setGridColor(Color.gray);
        tablaCaminos.setSelectionBackground(new Color(38,198,218));


        tablaCaminos.setShowGrid(true);
        tablaCaminos.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaCaminos.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaCaminos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaCaminos.setDefaultRenderer(Object.class, centerRenderer);

        tablaCaminos.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaCaminos);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaCaminos;

        /*tablaCaminos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tablaCaminos.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println(tablaCaminos.getValueAt(tablaCaminos.getSelectedRow(), 0).toString());
                }
            }
        });*/

        tablaCaminos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    for (Component component : panel.getParent().getComponents()) {
                        if(component.getName() == "panel datos buscar caminos" ){
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
                    System.out.println(tablaCaminos.getValueAt(tablaCaminos.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;
    }

    public void actualizarTablaCaminos() {

        panel.remove(1);
        panel.add(this.crearTablaCaminos(GestorCaminos.getGestor().listarCaminos()));
        panel.revalidate();

    }
    
    public static PantallaCaminos getSingle() {
    	return single;
    }
}
