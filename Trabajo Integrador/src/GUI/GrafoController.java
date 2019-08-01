package GUI;

import Dominio.Planta;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GrafoController {
    private PanelGrafoPlantas panel;
   // private TareaLogica logicaTareas;
    //private ProyectoLogica logicaProyecto;

    public GrafoController(PanelGrafoPlantas listener) {
        this.panel = listener;
       // this.logicaTareas = new TareaLogicaDefault();
       // this.logicaProyecto = new ProyectoLogicaDefault();
    }

    public void inicalizarVertices() {
        Runnable r = () -> {
            List<Planta> lista = Planta.getInstances();
            int y = 100;
            int x = 0;
            int i = 0;
            Color c = null;
            for(Planta p : lista){
                i++;
                x +=30;
                if( i % 2 == 0 ) {
                    y = 100;
                    c = Color.BLUE;
                } else {
                    y = 200;
                    c = Color.RED;
                }

                VerticeView v = new VerticeView(x,y,c);
                v.setId(p.getId());
                v.setNombre(p.getNombre());
                panel.agregar(v);
            }
            try {
                SwingUtilities.invokeAndWait(() -> {
                    panel.repaint();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread hilo = new Thread(r);

        hilo.start();
    }
}