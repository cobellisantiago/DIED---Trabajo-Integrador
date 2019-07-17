package Dominio.Unidades;

public class UnidadDeCantidad extends UnidadDeMedida{

    public UnidadDeCantidad(Unidad unidad){
        this.unidad = unidad;
    }


    public double factor(){
        if(this.unidad==Unidad.PIEZA){
            return 1;
        }
        return 0;
    }
}