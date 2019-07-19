package GUI;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaInsumo implements ActionListener{
    private static PantallaInsumo single;
    JPanel panel;
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
        //menu.setBorderPainted(false);
        menu.setBorder(null);
//button.setFocusable(false);
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

        //TODO
        // Obetner la lista de insumos en el sistema, junto con su id, nombre y la cantidad total de stock (suma de stock en las plantas)

        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"},{"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"},{"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"},{"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"Id","Nombre","Stock"};

        JTable tablaInsumos = new JTable(data,column);
        tablaInsumos.setRowHeight(50);
        tablaInsumos.setGridColor(Color.black);
        tablaInsumos.setFont(new Font("Roboto",Font.PLAIN,15 ));
        tablaInsumos.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 20));
        JScrollPane sp=new JScrollPane(tablaInsumos);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel2.add(sp, "span 2 3, grow , wrap");
        panel2.setBorder(BorderFactory.createTitledBorder("Panel de TABLE"));
        panel.add(panel2);
        JPanel panel3 = new JPanel(new MigLayout());

        /*panel3.add(new JLabel("First Name:"));
        panel3.add(new JTextField(30));
        panel3.add(new JLabel("Last Name:"),       "gap unrelated");
        panel3.add(new JTextField(30),   "wrap");
        panel3.add(new JLabel("Address"));
        panel3.add(new JTextField(),    "span, grow");*/

        panel.add(panel2);
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
        }
    }
}
