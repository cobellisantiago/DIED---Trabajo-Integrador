package Dominio;

import Dominio.Unidades.UnidadDeMedida;

public class InsumoLiquido extends Insumo{

    public Double densidad;

    public InsumoLiquido(){};

    public InsumoLiquido(int idInsumo, String descrip, Double costoActual, Double stockActual,
                         Double peso, Boolean refrigeracion, UnidadDeMedida unidad, Double densidad){

        super(idInsumo,descrip,costoActual,stockActual,
                peso,refrigeracion,unidad);

        this.densidad = densidad;

    };


    public double calcularPeso(){
        return stock*(unidadMedida.factor())*densidad;
    }

    public double calcularPeso(double vol){
        return stock*(unidadMedida.factor())*vol*0.0001;
    }


}