package GUI.Insumos;

import Dominio.Insumo;
import Gestores.GestorInsumos;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("ALL")
public class PantallaInsumo implements ActionListener{
    private static PantallaInsumo single;
    JPanel panel;
    JTable tabla;
    JPanel panelBuscar;
    JButton editar;
    JButton eliminar;
    String insumoSeleccionado;

    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "BuscarInsumo";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaInsumo(){}

    public static PantallaInsumo crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaInsumo();
        }
        single.agregarPantalla(p);
        return single;
    }
    public static void crearPantallaBuscar(JPanel p){

        single.agregarPantallaBuscar(p);

    }
    public static void crearPantallaCrear(JPanel p){

        PantallaCrearInsumo.crearPantalla(p);

    }

    public static PantallaInsumo getSingle() {	return single;}
    
    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        panel = new JPanel(new BorderLayout(10,10));

        JLabel tituloLabel = new JLabel("Insumos");
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

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setName(BUSCAR);
        buscarButton.addActionListener(this);

        JPanel panelBotones = new JPanel(new MigLayout("fill","[][grow]"));
        panelBotones.add(tituloLabel,"span, center");
        panelBotones.add(menu, "left");
        panelBotones.add(crear,"tag crear,span,split 2,center,gaptop 10,gapbottom 10, sizegroup bttn");
        panelBotones.add(buscarButton,"tag buscar, sizegroup bttn");

        //panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panelBotones.setBackground(new Color(207,216,220));
        panel.add(panelBotones,BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new MigLayout("fillx","[][grow][]"));
        crearTablaInsumos(GestorInsumos.getGestor().listarInsumos());
        //TODO
        // Obetner la lista de insumos en el sistema, junto con su id, nombre y la cantidad total de stock (suma de stock en las plantas)

        /*panel3.add(new JLabel("First Name:"));
        panel3.add(new JTextField(30));
        panel3.add(new JLabel("Last Name:"),       "gap unrelated");
        panel3.add(new JTextField(30),   "wrap");
        panel3.add(new JLabel("Address"));
        panel3.add(new JTextField(),    "span, grow");*/

        panel.add(crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()),BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));
        panel.setBackground(new Color(207,216,220));

        p.add(panel, "Insumos");
    }

    public void actionPerformed(ActionEvent e){
        String button = ((JButton) e.getSource()).getName();//Tenes que setearle el nombre al boton con setName() es para no depender del texto
        if(button == MENU) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Menu");
        }
        else if(button == CREAR) {
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "CrearInsumo");

        }else if(button == "BuscarInsumo"){
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());

            pane.show(p, BUSCAR);

        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Insumos");
            tabla.clearSelection();
            actualizarTablaInsumos();

        }/*else if(button == "Buscar1") {
            //TODO BUSCAR SEGUN CRITERIOS -- TENER EN CUENTA QUE CAPAZ ES NECESARIO HACER OTRA CLASE
            JPanel p = (JPanel) panel.getParent();
            CardLayout pane = (CardLayout) (p.getLayout());
            pane.show(p, BUSCAR);*/

    }

    public JPanel crearTablaInsumos(Object[][] data){

        JPanel panel = new JPanel(new MigLayout(" fillx","[][grow][]"));
        panel.setBackground(new Color(207,216,220));
        //Object[][] data = GestorInsumos.getGestor().listarInsumos();
        String columns[]={"Id","Nombre","Costo por Unidad","Necesita refrigeracion?","Peso","Stock"};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class, Double.class, Boolean.class, Double.class, Double.class
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

        tablaInsumos.getColumnModel().getColumn(0).setPreferredWidth(1); // Ancho columna id
       // tablaInsumos.getColumnModel().getColumn(3).setPreferredWidth(100); // Ancho columna refrigeracion
        tablaInsumos.getColumnModel().getColumn(4).setPreferredWidth(5); // Ancho columna peso
        tablaInsumos.getColumnModel().getColumn(5).setPreferredWidth(5); // Ancho columna stock

        tablaInsumos.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaInsumos);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaInsumos;

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
                    insumoSeleccionado = tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 1).toString();
                    System.out.println(tablaInsumos.getValueAt(tablaInsumos.getSelectedRow(), 0).toString());
                }
            }
        });

        return panel;
    }

    public void agregarPantallaBuscar(JPanel p){

        panelBuscar = new JPanel(new MigLayout(" fill, insets 0"));


        JPanel panel1 = new JPanel(new MigLayout(""));
        panel1.setName("panel datos buscar insumo");


        JLabel titulo = new JLabel("Buscar Insumo");

        JLabel nombreLabel = new JLabel("Nombre: ");
        JTextField nombreTextField = new JTextField(30);
        JLabel costoLabel = new JLabel("Costo: ");
        JTextField costoMinimoTextField = new JTextField("Minimo", 10);
        costoMinimoTextField.setForeground(Color.gray);
        JTextField costoMaximoTextField = new JTextField("Maximo", 10);
        costoMaximoTextField.setForeground(Color.gray);
        JLabel stockLabel = new JLabel("Stock: ");
        JTextField stockMinimoTextField = new JTextField("Minimo", 10);
        stockMinimoTextField.setForeground(Color.gray);
        JTextField stockMaximoTextField = new JTextField("Maximo", 10);
        stockMaximoTextField.setForeground(Color.gray);
        
        /*ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(nombreRadioButton);
        buttonGroup.add(costoRadioButton);
        buttonGroup.add(stockRadioButton);*/
        //minimoTextField.addFocusListener();

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setName("Buscar");

        nombreTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                costoMaximoTextField.setEnabled(false);
                costoMinimoTextField.setEnabled(false);
                stockMinimoTextField.setEnabled(false);
                stockMaximoTextField.setEnabled(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(!nombreTextField.getText().isEmpty()){
                    costoMaximoTextField.setEnabled(false);
                    costoMinimoTextField.setEnabled(false);
                    stockMinimoTextField.setEnabled(false);
                    stockMaximoTextField.setEnabled(false);
                }else{
                    costoMaximoTextField.setEnabled(true);
                    costoMinimoTextField.setEnabled(true);
                    stockMinimoTextField.setEnabled(true);
                    stockMaximoTextField.setEnabled(true);
                }
            }
        });

        costoMinimoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(costoMinimoTextField.getText().equals("Minimo")) {
                    costoMinimoTextField.setText("");
                    costoMinimoTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(costoMinimoTextField.getText().isEmpty()){
                    costoMinimoTextField.setText("Minimo");
                    costoMinimoTextField.setForeground(Color.gray);
                    if(costoMaximoTextField.getText().equals("Maximo")) {
                        stockMinimoTextField.setEnabled(true);
                        stockMaximoTextField.setEnabled(true);
                        nombreTextField.setEnabled(true);
                    }
                }else{
                    stockMinimoTextField.setEnabled(false);
                    stockMaximoTextField.setEnabled(false);
                    nombreTextField.setEnabled(false);
                }
            }
        });
        costoMaximoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(costoMaximoTextField.getText().equals("Maximo")) {
                    costoMaximoTextField.setText("");
                    costoMaximoTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(costoMaximoTextField.getText().isEmpty()){
                    costoMaximoTextField.setText("Maximo");
                    costoMaximoTextField.setForeground(Color.gray);
                    if (costoMinimoTextField.equals("Minimo")) {
                        stockMinimoTextField.setEnabled(true);
                        stockMaximoTextField.setEnabled(true);
                        nombreTextField.setEnabled(true);
                    }
                }else{
                    stockMinimoTextField.setEnabled(false);
                    stockMaximoTextField.setEnabled(false);
                    nombreTextField.setEnabled(false);
                }
            }
        });

        stockMinimoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(stockMinimoTextField.getText().equals("Minimo")) {
                    stockMinimoTextField.setText("");
                    stockMinimoTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(stockMinimoTextField.getText().isEmpty()){
                	stockMinimoTextField.setText("Minimo");
                	stockMinimoTextField.setForeground(Color.gray);
                    if (stockMaximoTextField.equals("Maximo")) {
                        costoMaximoTextField.setEnabled(true);
                        costoMinimoTextField.setEnabled(true);
                        nombreTextField.setEnabled(true);
                    }
                }else{
                    costoMaximoTextField.setEnabled(false);
                    costoMinimoTextField.setEnabled(false);
                    nombreTextField.setEnabled(false);
                }
            }
        });
        stockMaximoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (stockMaximoTextField.getText().equals("Maximo")) {
                    stockMaximoTextField.setText("");
                    stockMaximoTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(stockMaximoTextField.getText().isEmpty()){
                	stockMaximoTextField.setText("Maximo");
                	stockMaximoTextField.setForeground(Color.gray);
                	if(stockMinimoTextField.equals("Minimo")) {
                        costoMaximoTextField.setEnabled(true);
                        costoMinimoTextField.setEnabled(true);
                        nombreTextField.setEnabled(true);
                    }
                }else{
                    costoMaximoTextField.setEnabled(false);
                    costoMinimoTextField.setEnabled(false);
                    nombreTextField.setEnabled(false);
                }
            }
        });
        
        titulo.setFont(new Font("Roboto",Font.PLAIN,20));
        titulo.setForeground(Color.BLUE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panelBuscar.add(titulo,"span, growx, gapbottom 15, gaptop 5");

        panel1.add(nombreLabel,"right, align label");
        panel1.add(nombreTextField);
        panel1.add(costoLabel, "right, align label");
        panel1.add(costoMinimoTextField,"left,split 3");
        panel1.add(new JLabel("-"),"left, align label");
        panel1.add(costoMaximoTextField,"left, wrap");
        panel1.add(stockLabel,"newline, right, align label");
        panel1.add(stockMinimoTextField,"left, split 3");
        panel1.add(new JLabel("-"),"left, align label");
        panel1.add(stockMaximoTextField,"left");

        editar = new JButton("Editar");
        eliminar = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelDialog = new JPanel(new MigLayout("fill, insets 0","[][]"));
                JDialog dialog = new JDialog();

                dialog.setSize(400, 200);
                JLabel preguntaLabel= new JLabel("¿Seguro que desea eliminar el");
                preguntaLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(preguntaLabel,"span,center,wrap");
                JLabel insumoLabel= new JLabel("insumo "+insumoSeleccionado+" ?");
                insumoLabel.setFont(new Font("Roboto",Font.BOLD,15));
                panelDialog.add(insumoLabel,"span,center,wrap,gaptop 0");



                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorInsumos.getGestor().eliminiarPorNombre(insumoSeleccionado);
                        actualizarTablaInsumos();
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
                panelDialog.setBackground(new Color(207,216,220));
                dialog.setSize(500, 300);
                JPanel panelDatos = new JPanel (new MigLayout(""));
                //panelDatos.setBackground(Color.white);
                JLabel tituloLabel = new JLabel("Editar Insumo: "+insumoSeleccionado);
                JLabel descripcionLabel = new JLabel("Descripcion: ");
                JLabel costoLabel = new JLabel("Costo por Unidad: ");
                JLabel refrigeracionLabel = new JLabel("Necesita refrigeracion? ");
                JLabel pesoLabel = new JLabel("Peso: ");

                tituloLabel.setFont(new Font("Roboto",Font.PLAIN,20));
                tituloLabel.setForeground(Color.BLUE);
                tituloLabel.setHorizontalAlignment(JLabel.CENTER);
                panelDatos.add(tituloLabel,"span, growx, gapbottom 15, gaptop 5");

                JTextField descrip = new JTextField(30);
                JTextField costo = new JTextField(10);;
                JCheckBox refrige = new JCheckBox();
                JTextField peso = new JTextField(10);;
                JTextField unidad = new JTextField(20);;

                panelDatos.add(descripcionLabel,"align label");
                panelDatos.add(descrip, "wrap");
                panelDatos.add(costoLabel,"align label");
                panelDatos.add(costo,"wrap");
                panelDatos.add(refrigeracionLabel,"align label");
                panelDatos.add(refrige, "wrap");
                panelDatos.add(pesoLabel,"align label");
                panelDatos.add(peso,"wrap");

                Insumo insumoAEditar = GestorInsumos.getGestor().getInsumoPorNombre(insumoSeleccionado);
                descrip.setText(insumoAEditar.getDescripcion());
                costo.setText(insumoAEditar.getCosto().toString());
                refrige.setSelected(insumoAEditar.getEsRefrigerado());
                peso.setText(insumoAEditar.getPeso().toString());

                panelDialog.add(panelDatos,"center, span");

                JButton aceptar = new JButton("Aceptar");

                aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GestorInsumos.getGestor().editarPorNombre(insumoSeleccionado,descrip.getText(),
                                Double.valueOf(costo.getText()),Double.valueOf(peso.getText()),refrige.isSelected());
                        actualizarTablaInsumos();
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

        panel1.add(editar,"tag editar,span, right,split 3, sizegroup bttn");
        panel1.add(eliminar,"tag elimninar, sizegroup bttn");
        panel1.add(buscarButton,"tag buscar, sizegroup bttn");



        panelBuscar.add(panel1,"wrap");
        panelBuscar.add(this.crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()), "span, grow");

        JButton cancelarBoton = new JButton("Cancelar");
        cancelarBoton.setName("Cancelar");
        cancelarBoton.addActionListener(this);
        panelBuscar.add(cancelarBoton,"span, wrap, right,gapright 10");

        p.add(panelBuscar,"BuscarInsumo");

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nombreTextField.getText().isEmpty()){//nombreRadioButton.isSelected()){
                    System.out.println("Texto en nombre: " + nombreTextField.getText());
                    panelBuscar.remove(2);
                    panelBuscar.add(single.crearTablaInsumos(GestorInsumos.getGestor().buscarNombre(nombreTextField.getText())),"span, grow",2);
                    panelBuscar.revalidate();
                }
                else if(!costoMinimoTextField.getText().equals("Minimo") || !costoMaximoTextField.getText().equals("Maximo")){//costoRadioButton.isSelected()) {
                	Double minimo;
                	Double maximo;
                	try {
                		minimo = Double.valueOf(costoMinimoTextField.getText());
                	}	catch(NumberFormatException a){
                		minimo = Double.NEGATIVE_INFINITY;
                	}
                	try {
                		maximo = Double.valueOf(costoMaximoTextField.getText());
                	}	catch(NumberFormatException a){
                		maximo = Double.POSITIVE_INFINITY;
                	}
                	panelBuscar.remove(2);
                    panelBuscar.add(single.crearTablaInsumos(GestorInsumos.getGestor().buscarCosto(minimo, maximo)),"span, grow",2);
                    panelBuscar.revalidate();
                }
                else if(!stockMinimoTextField.getText().equals("Minimo") || stockMaximoTextField.getText().equals("Maximo")){//stockRadioButton.isSelected()) {
                	Double minimo;
                	Double maximo;
                	try {
                		minimo = Double.valueOf(stockMinimoTextField.getText());
                	}	catch(NumberFormatException a){
                		minimo = Double.NEGATIVE_INFINITY;
                	}
                	try {
                		maximo = Double.valueOf(stockMaximoTextField.getText());
                	}	catch(NumberFormatException a){
                		maximo = Double.POSITIVE_INFINITY;
                	}
                	panelBuscar.remove(2);
                	panelBuscar.add(single.crearTablaInsumos(GestorInsumos.getGestor().buscarStock(minimo, maximo)),"span, grow",2);
                	panelBuscar.revalidate();
                }
                editar.setEnabled(false);
                eliminar.setEnabled(false);
            }
        });

    }

    public void actualizarTablaInsumos(){

        panel.remove(1);
        panel.add(this.crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()));
        panel.revalidate();
        actualizarTablaInsumosBuscar(panelBuscar);

    }

    public void actualizarTablaInsumosBuscar(JPanel panelTabla){

        panelTabla.remove(2);
        panelTabla.add(this.crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()),"span, grow",2);
        panelTabla.revalidate();
    }
}
