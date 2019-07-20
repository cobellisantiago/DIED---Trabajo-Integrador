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
    final static String BUSCAR = "Buscar";
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

        JPanel panelBotones = new JPanel();
        panelBotones.add(menu);
        panelBotones.add(crear);
        panelBotones.add(new JButton(BUSCAR));
        panelBotones.add(new JButton(EDITAR));
        panelBotones.add(new JButton(BORRAR));

        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10, 5, 5, 10);
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        panelBotones.setBorder(BorderFactory.createTitledBorder("Panel de Botones"));
        panel.add(panelBotones,BorderLayout.NORTH);

        c.fill=GridBagConstraints.BOTH;
        c.gridx=0;
        c.gridy=1;

        JPanel panel2 = new JPanel(new MigLayout("debug, fillx","[][grow][]"));
        crearTablaInsumos();
        //TODO
        // Obetner la lista de insumos en el sistema, junto con su id, nombre y la cantidad total de stock (suma de stock en las plantas)

        /*panel3.add(new JLabel("First Name:"));
        panel3.add(new JTextField(30));
        panel3.add(new JLabel("Last Name:"),       "gap unrelated");
        panel3.add(new JTextField(30),   "wrap");
        panel3.add(new JLabel("Address"));
        panel3.add(new JTextField(),    "span, grow");*/

        panel.add(crearTablaInsumos());
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
            panel.add(this.crearTablaInsumos());
            ((AbstractTableModel) tabla.getModel()).fireTableDataChanged();

            System.out.println("tabla actualizada");
        }
    }

    public JPanel crearTablaInsumos(){

        JPanel panel = new JPanel(new MigLayout("debug, fillx","[][grow][]"));

        Object[][] data = GestorInsumos.getGestor().listarInsumos();
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
        panel.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        tabla=tablaInsumos;

        return panel;
    }


}
