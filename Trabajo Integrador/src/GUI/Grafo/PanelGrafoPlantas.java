package GUI.Grafo;

import javax.swing.*;

import Dominio.Camino;
import Estructuras.Vertice;
import Gestores.GestorCaminos;
import Gestores.GestorPlantas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
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

    private PanelGrafoPlantas(){

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
        this.controller.inicalizarAristas();

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && !event.isConsumed()) {
                    event.consume();
                    VerticeView v = clicEnUnNodo(event.getPoint());
                    if(v != null) {
                        aristaSeleccionada = v;
                        aristaSeleccionada.setColor(Color.MAGENTA);
                        actualizarVertice(aristaSeleccionada, event.getPoint());
                        actualizarArista(aristaSeleccionada);
                        repaint();
                    }
                    //System.out.println("DOBLE CLICK");
                }
            }

            public void mouseReleased(MouseEvent event) {
                //System.out.println("mouseReleased: "+event.getPoint());
                if(aristaSeleccionada != null) {
                    aristaSeleccionada.setColor(Color.PINK);
                    actualizarVertice(aristaSeleccionada, event.getPoint());
                    actualizarArista(aristaSeleccionada);
                    repaint();
                }
                aristaSeleccionada = null;
                arrastrando = false;
            }


        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                if(aristaSeleccionada != null) {
                    actualizarVertice(aristaSeleccionada ,event.getPoint());
                    actualizarArista(aristaSeleccionada);
                    repaint();
                }
            }
        });
        
        single = this;
    }
   
    
    public static PanelGrafoPlantas getSingle() {
    	if(single == null) return new PanelGrafoPlantas();
    	return single;
    }
    
    public static void deleteSingle() {
    	single = null;
    }

    public void setNormalColor() {
    	for(VerticeView ver : vertices)	ver.setColor(Color.PINK);
    	for(AristaView ari : aristas)	ari.setColor(Color.BLACK, Color.GRAY);
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
            Camino camino = GestorCaminos.getGestor().getCamino(GestorPlantas.getGestor().getPlanta(a.getOrigen().getId()), GestorPlantas.getGestor().getPlanta(a.getDestino().getId()));
            Integer textX = a.getOrigen().getCoordenadaX() + (a.getDestino().getCoordenadaX() - a.getOrigen().getCoordenadaX()) / 2;
            Integer textY = a.getOrigen().getCoordenadaY() + (a.getDestino().getCoordenadaY() - a.getOrigen().getCoordenadaY()) / 2;   
            g2d.drawString("(D "+camino.getDistancia().toString()+")", textX, textY);
            g2d.drawString("( T "+camino.getDuracion().toString()+")", textX, textY-10);
            g2d.drawString("( PM "+camino.getPesoMaximo().toString()+")", textX, textY-20);

            //g2d.drawString("(D "+camino.getDistancia().toString()+")( T "+camino.getDuracion().toString()+")( PM "+camino.getPesoMaximo().toString()+")", textX, textY);
            //dibujo una flecha al final
            // con el color del origen para que se note
            g2d.setPaint(Color.BLACK);
            Polygon flecha = new Polygon();
            /*Integer vectorX = a.getDestino().getCoordenadaX() - a.getOrigen().getCoordenadaX();
            Integer vectorY = a.getDestino().getCoordenadaY() - a.getOrigen().getCoordenadaY();
            Integer vectorModulo = (int) Math.round(Math.sqrt(vectorX * vectorX + vectorY * vectorY));
            Integer X1 = a.getDestino().getCoordenadaX() + vectorX * 1 / vectorModulo;
            Integer Y1 = a.getDestino().getCoordenadaY() + vectorY * 1 / vectorModulo;
            Integer vectorNormalX = -vectorY;
            Integer vectorNormalY = vectorX;
            Integer X2 = X1 - vectorX * 10 / vectorModulo + vectorNormalX * 5 / vectorModulo;
            Integer Y2 = Y1 - vectorY * 10 / vectorModulo + vectorNormalY * 5 / vectorModulo;
            Integer X3 = X1 - vectorX * 10 / vectorModulo - vectorNormalX * 5 / vectorModulo;
            Integer Y3 = Y1 - vectorY * 10 / vectorModulo - vectorNormalY * 5 / vectorModulo;
            flecha.addPoint(X1, Y1);
            flecha.addPoint(X2, Y2);
            flecha.addPoint(X3, Y3);*/
            
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+7);
            flecha.addPoint(a.getDestino().getCoordenadaX()+20, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+10);
            
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
        dibujarAristas(g2d);
    }

    public Dimension getPreferredSize() {
        return new Dimension(900, 400);
    }

    private void actualizarVertice(VerticeView v, Point puntoNuevo) {
        xRepintado = puntoNuevo.x;
        yRepintado = puntoNuevo.y;
        v.setCoordenadaX(xRepintado);
        v.setCoordenadaY(yRepintado);
        v.update();
    }
    
    public void actualizarArista(VerticeView v) {
    	for(AristaView a : aristas) {
        	if(a.getOrigen().equals(v) || a.getDestino().equals(v)) {
        		a.update();
        	}
        }
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

    public VerticeView getVertice(Integer id) {
    	for(VerticeView i : vertices)	if(i.getId().equals(id))	return i;
    	return null;
    }
    
    public AristaView getArista(Integer id1, Integer id2) {
    	VerticeView vertice1 = getVertice(id1);
    	VerticeView vertice2 = getVertice(id2);
    	for(AristaView i : aristas)	if(i.getOrigen().equals(vertice1) && i.getDestino().equals(vertice2))	return i;
    	return null;
    }
    
    /*public static PanelGrafoPlantas crearPanel(JPanel p){
        if(single == null) {
            single = new PanelGrafoPlantas();
            single.dibujarPanel(p);
        }
        return single;
    }

    private void dibujarPanel(JPanel panel){

    }*/

}
