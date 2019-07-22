package GUI;

import Gestores.GestorInsumos;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class PantallaInsumo implements ActionListener{
    private static PantallaInsumo single;
    JPanel panel;
    JTable tabla;
    final static String MENU = "Menu";
    final static String CREAR = "Crear";
    final static String BUSCAR = "BuscarInsumo";
    final static String EDITAR = "Editar";
    final static String BORRAR = "Borrar";

    private PantallaInsumo(){}

    public static PantallaInsumo crearPantalla(JPanel p){
        if(single == null) {
            single = new PantallaInsumo();
            single.agregarPantalla(p);
        }
        return single;
    }
    public static void crearPantallaBuscar(JPanel p){

        single.agregarPantallaBuscar(p);

    }

    @SuppressWarnings("Duplicates")
    public void agregarPantalla(JPanel p) {
        panel = new JPanel(new BorderLayout(10,10));


        ImageIcon backIcon = new ImageIcon(new ImageIcon("src/GUI/Icons/back-arrow.png").getImage()
                .getScaledInstance(45, 40, Image.SCALE_DEFAULT));

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

        JPanel panelBotones = new JPanel();
        panelBotones.add(menu);
        panelBotones.add(crear);
        panelBotones.add(buscarButton);
        panelBotones.add(new JButton(EDITAR));
        panelBotones.add(new JButton(BORRAR));

        panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
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

        panel.add(crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()));
        panel.setBorder(BorderFactory.createTitledBorder("Panel de INSUMOS"));

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
            PantallaCrearInsumo.crearPantalla();

           panel.remove(1);
           panel.add(this.crearTablaInsumos(GestorInsumos.getGestor().listarInsumos()));
           //((AbstractTableModel) tabla.getModel()).fireTableDataChanged();
            panel.revalidate();
        }else if(button == "BuscarInsumo"){
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, BUSCAR);
        }else if(button == "Cancelar"){
            JPanel p = (JPanel)panel.getParent();
            CardLayout pane = (CardLayout)(p.getLayout());
            pane.show(p, "Insumos");

        }else if(button == "Buscar1") {
            //TODO BUSCAR SEGUN CRITERIOS -- TENER EN CUENTA QUE CAPAZ ES NECESARIO HACER OTRA CLASE
            JPanel p = (JPanel) panel.getParent();
            CardLayout pane = (CardLayout) (p.getLayout());
            pane.show(p, BUSCAR);
        }
    }

    public void focusGained(FocusEvent e) {
            JTextField source = (JTextField)e.getComponent();
            source.setText("");
            source.setForeground(Color.black);
            //source.removeFocusListener(this);
    }

    public void focusLost(FocusEvent e) {
            JTextField source = (JTextField)e.getComponent();
            if(source.getText().isEmpty()) {
                System.out.println("El texto esta vacio");
                source.setText("Minimo");
                source.setForeground(Color.gray);
                //source.removeFocusListener(this);
            }
    }

    public JPanel crearTablaInsumos(Object[][] data){

        JPanel panel = new JPanel(new MigLayout("debug, fillx","[][grow][]"));

        //Object[][] data = GestorInsumos.getGestor().listarInsumos();
        String columns[]={"Id","Nombre","Stock"};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class, Double.class
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

        tablaInsumos.setShowGrid(true);
        tablaInsumos.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaInsumos.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        ((JLabel)tablaInsumos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tablaInsumos.setAutoCreateRowSorter(true);
        JScrollPane sp=new JScrollPane(tablaInsumos);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(sp, "span 2 3, grow , wrap");
        //panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaInsumos;

        return panel;
    }

    public void agregarPantallaBuscar(JPanel p){

        JPanel panelBuscar = new JPanel(new MigLayout("fill, insets 0"));

        JPanel panel1 = new JPanel(new MigLayout(""));

        JLabel titulo = new JLabel("Buscar Insumo");


        JTextField minimoTextField = new JTextField("Mínimo",10);
        minimoTextField.setForeground(Color.gray);
        //minimoTextField.addFocusListener();

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setName("Buscar");

        JTextField nombreTextField = new JTextField(30);

        titulo.setFont(new Font("Roboto",Font.PLAIN,20));
        titulo.setForeground(Color.BLUE);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        panelBuscar.add(titulo,"span, growx, gapbottom 15, gaptop 5");

        panel1.add(new JLabel("Nombre: "),"right, align label");
        panel1.add(nombreTextField);
        panel1.add(new JLabel("Costo: "), "right, align label");

        panel1.add(minimoTextField,"left,split 3");
        panel1.add(new JLabel("-"),"left, align label");
        panel1.add(new JTextField("Maximo",10),"left, wrap");
        panel1.add(new JLabel("Stock: "),"newline, right, align label");
        panel1.add(new JTextField("Minimo",10),"left, split 3");
        panel1.add(new JLabel("-"),"left, align label");
        panel1.add(new JTextField("Maximo",10),"left");

        panel1.add(buscarButton,"span, right");

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
                if(!nombreTextField.getText().isEmpty()){
                    System.out.println(nombreTextField.getText());
                    panelBuscar.remove(2);
                    panelBuscar.add(single.crearTablaInsumos(GestorInsumos.getGestor().buscar(nombreTextField.getText())),"span, grow",2);
                    panelBuscar.revalidate();
                }

            }
        });

    }
}
