
package GUI.Plantas;

import Gestores.GestorInsumos;
import Gestores.GestorPlantas;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dominio.Insumo;
import Dominio.Planta;
import GUI.PantallaBase;

public class PantallaPlantas implements ActionListener{


    private static PantallaPlantas single;
    JPanel panel;
    JPanel panelGrafo;
    JTable tabla;

    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "Buscar";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaPlantas(){}

    public static PantallaPlantas crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaPlantas();
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

        JLabel tituloLabel = new JLabel("Plantas");
        tituloLabel.setFont(new Font("Roboto",Font.BOLD,32));


        ImageIcon backIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/left-arrow.png").getImage()
                .getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        ImageIcon reloadIcon = new ImageIcon(new ImageIcon("Trabajo Integrador/src/GUI/Icons/reload.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_DEFAULT));

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

        JButton mapaButton = new JButton("Mapa");
        mapaButton.setName("MapaPlanta");
        mapaButton.addActionListener(this);
        
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelDialog = new JPanel(new MigLayout("fill, insets 0","[][]"));
                JDialog dialog = new JDialog();
                String plantaSeleccionada = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                dialog.setSize(400, 200);
                JLabel preguntaLabel= new JLabel("�Seguro que desea eliminar la");
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(preguntaLabel,"span,center,wrap");
                JLabel plantaLabel= new JLabel("planta "+plantaSeleccionada+" ?");
                plantaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(plantaLabel,"span,center,wrap,gaptop 0");



                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorPlantas.getGestor().eliminiar((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0));
                        actualizarTablaPlantas();
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
                String plantaSeleccionada = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
                panelDialog.setBackground(new Color(207,216,220));
                dialog.setSize(500, 300);
                JPanel panelDatos = new JPanel (new MigLayout(""));
                //panelDatos.setBackground(Color.white);
                JLabel tituloLabel = new JLabel("Editar Planta: "+plantaSeleccionada);
                JLabel descripcionLabel = new JLabel("Nombre: ");
                tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
                tituloLabel.setForeground(Color.BLUE);
                tituloLabel.setHorizontalAlignment(JLabel.CENTER);
                panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

                JTextField descrip = new JTextField(30);

                panelDatos.add(descripcionLabel,"align label");
                panelDatos.add(descrip, "wrap");

                Planta plantaAEditar = GestorPlantas.getGestor().getPlanta((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0));
                descrip.setText(plantaAEditar.getNombre());

                panelDialog.add(panelDatos,"center, span");

                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorPlantas.getGestor().editar((Integer)tabla.getValueAt(tabla.getSelectedRow(), 0),descrip.getText());
                        actualizarTablaPlantas();
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

        JButton pageRank = new JButton("Page Rank");

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.setName("panel datos buscar planta");
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 6,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(editar,"tag editar, sizegroup bttn");
        panelBotones.add(eliminar,"tag eliminar, sizegroup bttn");
        panelBotones.add(mapaButton,"tag mapa, sizegroup bttn");
        panelBotones.add(pageRank,"tag pageRank, sizegroup bttn");

        pageRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(1);
                panel.add(crearTablaPlantasPage(GestorPlantas.getGestor().pageRank()));
                JButton resetButton = new JButton(reloadIcon);
                resetButton.setBorderPainted(false);
                resetButton.setBorder(null);
                resetButton.setFocusable(false);
                resetButton.setMargin(new Insets(0, 0, 0, 0));
                resetButton.setContentAreaFilled(false);
                resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panelBotones.remove(resetButton);
                        actualizarTablaPlantas();
                        panelBotones.revalidate();
                        panelBotones.repaint();

                    }
                });
                panelBotones.add(resetButton,"sizegroup bttn");
                panel.revalidate();

            }
        });


        //panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        panel.add(crearTablaPlantas(GestorPlantas.getGestor().listarPlantas()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));
        panel.setBackground(new Color(207,216,220));


        p.add(panel, "Plantas");
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
            pane.show(p, "CrearPlanta");
            //Operaciones de crear, buscar, editar y borrar.

        }else if(button == "MapaPlanta") {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            PantallaBase.getPanel().remove(PantallaMapaPlanta.getSingle().getPanelGeneral());
            PantallaMapaPlanta.crearPantalla(PantallaBase.getPanel());
            pane.show(p, "MapaPlanta");

        }
    }

    public JPanel crearTablaPlantas(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        //Object[][] data = GestorPlantas.getGestor().listarPlantas();
        String columns[]={"Id","Nombre"/*,"Stock"*/};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class
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


        JTable tablaPlantas = new JTable(model);
        tablaPlantas.setRowHeight(35);
        tablaPlantas.setGridColor(Color.gray);
        tablaPlantas.setSelectionBackground(new Color(38,198,218));



        tablaPlantas.setShowGrid(true);
        tablaPlantas.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaPlantas.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaPlantas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaPlantas.setDefaultRenderer(Object.class, centerRenderer);

        tablaPlantas.getColumnModel().getColumn(0).setPreferredWidth(10); // Ancho columna id

        tablaPlantas.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaPlantas);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaPlantas;

        /*tablaPlantas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tablaPlantas.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println(tablaPlantas.getValueAt(tablaPlantas.getSelectedRow(), 0).toString());
                }
            }
        });*/

        tablaPlantas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    for (Component component : panel.getParent().getComponents()) {
                        if(component.getName() == "panel datos buscar planta" ){
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
                    System.out.println(tablaPlantas.getValueAt(tablaPlantas.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;
    }

    public void actualizarTablaPlantas() {

        panel.remove(1);
        panel.add(this.crearTablaPlantas(GestorPlantas.getGestor().listarPlantas()));
        panel.revalidate();

    }

    private void agregarGrafo(){
        //if(this.panelGrafo == null) this.panelGrafo = new PanelGrafoPlantas();

        //panel.add(new PanelGrafoPlantas());
    }

    public JPanel crearTablaPlantasPage(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        //Object[][] data = GestorPlantas.getGestor().listarPlantas();
        String columns[]={"Id","Nombre","Page Rank"};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class, Integer.class
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


        JTable tablaPlantas = new JTable(model);
        tablaPlantas.setRowHeight(35);
        tablaPlantas.setGridColor(Color.gray);
        tablaPlantas.setSelectionBackground(new Color(38,198,218));


        tablaPlantas.setShowGrid(true);
        tablaPlantas.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaPlantas.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaPlantas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tablaPlantas.setDefaultRenderer(Object.class, centerRenderer);

        tablaPlantas.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaPlantas);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaPlantas;

        /*tablaPlantas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (tablaPlantas.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println(tablaPlantas.getValueAt(tablaPlantas.getSelectedRow(), 0).toString());
                }
            }
        });*/

        tablaPlantas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    for (Component component : panel.getParent().getComponents()) {
                        if(component.getName() == "panel datos buscar planta" ){
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
                    System.out.println(tablaPlantas.getValueAt(tablaPlantas.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;
    }

    public static PantallaPlantas getSingle() {
        return single;
    }


}

