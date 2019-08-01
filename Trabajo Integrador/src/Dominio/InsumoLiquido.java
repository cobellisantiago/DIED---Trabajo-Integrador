package Dominio;

import Dominio.Unidades.UnidadDeMedida;

public class InsumoLiquido extends Insumo{

    public Double densidad;

    //public InsumoLiquido(){};

    public InsumoLiquido(String descrip, Double costoActual, Double peso, Boolean refrigeracion, UnidadDeMedida unidad, Double densidad){

        super(descrip, costoActual, peso, refrigeracion);//, unidad);

        this.densidad = densidad;

    };


    public double calcularPeso(){
        //TODO REVISAR ESTO!!!
       // return stock*(unidadMedida.factor())*densidad;
        return 0;
    }

    public double calcularPeso(double vol){
        //TODO REVISAR ESTO!!
        //return stock*(unidadMedida.factor())*vol*0.0001;
        return 0;
    }


}