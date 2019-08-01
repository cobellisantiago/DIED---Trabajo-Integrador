package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PanelGrafoPlantas extends JPanel{

    private static PanelGrafoPlantas single;
    private java.util.Queue<Color> colaColores;

    private java.util.List<VerticeView> vertices;
    private List<AristaView> aristas;

    private GrafoController controller;

    private int xRepintado = 0;
    private int yRepintado = 0;

    private VerticeView aristaSeleccionada = null;

    private Boolean arrastrando = false;

    public PanelGrafoPlantas(){

       // this.framePadre = (JFrame) this.getParent();
        this.controller = new GrafoController(this);
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();


        this.colaColores = new LinkedList<Color>();
        this.colaColores.add(Color.RED);
        this.colaColores.add(Color.BLUE);
        this.colaColores.add(Color.ORANGE);
        this.colaColores.add(Color.CYAN);
        this.controller.inicalizarVertices();

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && !event.isConsumed()) {
                    event.consume();
                    VerticeView v = clicEnUnNodo(event.getPoint());
                    if(v != null) {
                        aristaSeleccionada = v;
                        aristaSeleccionada.setColor(Color.CYAN);
                        actualizarVertice(aristaSeleccionada, event.getPoint());
                    }
                    System.out.println("DOBLE CLICK");
                }
            }

            public void mouseReleased(MouseEvent event) {
                System.out.println("mouseReleased: "+event.getPoint());
                if(aristaSeleccionada != null) {
                    aristaSeleccionada.setColor(Color.BLUE);
                    actualizarVertice(aristaSeleccionada, event.getPoint());
                }
                aristaSeleccionada = null;
                arrastrando = false;
            }


        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                if(aristaSeleccionada != null) {
                    actualizarVertice(aristaSeleccionada ,event.getPoint());
                }
            }
        });
    }

    public void agregar(AristaView arista){
        this.aristas.add(arista);
    }

    public void agregar(VerticeView vert){
        this.vertices.add(vert);
    }

    private void dibujarVertices(Graphics2D g2d) {
        for (VerticeView v : this.vertices) {
            g2d.setPaint(Color.BLUE);
            g2d.drawString(v.etiqueta(),v.getCoordenadaX()+25,v.getCoordenadaY()+25);
            g2d.setPaint(v.getColor());
            g2d.fill(v.getNodo());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        for (AristaView a : this.aristas) {
            g2d.setPaint(a.getColor());
            g2d.setStroke ( a.getFormatoLinea());
            g2d.draw(a.getLinea());
            //dibujo una flecha al final
            // con el color del origen para que se note
            g2d.setPaint(Color.BLACK);
            Polygon flecha = new Polygon();
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+7);
            flecha.addPoint(a.getDestino().getCoordenadaX()+20, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+18);
            g2d.fillPolygon(flecha);
        }
    }

    private VerticeView clicEnUnNodo(Point p) {
        for (VerticeView v : this.vertices) {
            if (v.getNodo().contains(p)) {
                return v;
            }
        }
        return null;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarVertices(g2d);
        //  dibujarAristas(g2d);
    }

    public Dimension getPreferredSize() {
        return new Dimension(900, 400);
    }

    private void actualizarVertice(VerticeView v, Point puntoNuevo) {
        int OFFSET_X = v.getNombre().length()*20;
        int OFFSET_Y = 31;
        repaint(xRepintado-5,yRepintado-5,v.RADIO+OFFSET_X, v.RADIO + OFFSET_Y);
        xRepintado = puntoNuevo.x;
        yRepintado = puntoNuevo.y;
        v.setCoordenadaX(xRepintado);
        v.setCoordenadaY(yRepintado);
        v.update();
        repaint(xRepintado-5,yRepintado-5,v.RADIO+OFFSET_X, v.RADIO + OFFSET_Y);
    }

    private void dibujarFlecha(Graphics2D g2, Point tip, Point tail, Color color){
        double phi;
        int barb;
        phi = Math.toRadians(40);
        barb = 20;

        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        //System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        for(int j = 0; j < 2; j++)
        {
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - phi;
        }
    }

    public static PanelGrafoPlantas crearPanel(JPanel p){
        if(single == null) {
            single = new PanelGrafoPlantas();
            single.dibujarPanel(p);
        }
        return single;
    }

    private void dibujarPanel(JPanel panel){

    }

}
