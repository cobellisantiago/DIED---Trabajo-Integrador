package Dominio.Unidades;

public class UnidadDeSuperficie extends UnidadDeMedida{

    public UnidadDeSuperficie(Unidad unidad){
        this.unidad = unidad;
    }

    public double factor(){
        switch(this.unidad){
            case M2: return 1;
            case CM2: return 0.0001;
        }
        return 0;
    }
}