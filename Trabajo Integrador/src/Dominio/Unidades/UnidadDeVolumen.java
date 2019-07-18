package Dominio.Unidades;

public class UnidadDeVolumen extends UnidadDeMedida{

    public UnidadDeVolumen(Unidad unidad){
        this.unidad = unidad;
    }

    public double factor(){
        switch(this.unidad){
            case M3: return 1;
            case CM3: return 0.000001;
            case LITRO: return 0.001;
            default: return 0;
        }
    }

}
