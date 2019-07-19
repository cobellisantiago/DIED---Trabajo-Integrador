package Dominio;

import Dominio.Unidades.UnidadDeMedida;

import java.util.ArrayList;
import java.util.List;

public class Insumo implements Comparable<Insumo> {

    private static List<Insumo> instances = new ArrayList<Insumo>();
    protected int id;
    protected String descripcion;
    protected Double costo;
    //protected Double stock;
    protected Double peso; //en Kg
    protected Boolean esRefrigerado;
    protected UnidadDeMedida unidadMedida;
    


    public Insumo(String descrip, Double costoActual, Double pesoEnKg, Boolean refrigeracion){
        this.id = instances.size() + 1;
        this.descripcion = descrip;
        this.costo = costoActual;
        this.peso = pesoEnKg;
        this.esRefrigerado = refrigeracion;

        instances.add(this);
    }

    public Insumo(){
        instances.add(this);
    };

    public static List<Insumo> getInstances(){
        return instances;
    }

    public int compareTo(Insumo obj){
        // TODO Revisar esto!!!

        //return (int) (this.stock - obj.stock);

      /*if(this.stock > obj.stock)    return 1;
      if(this.stock < obj.stock)    return -1;
      return 0;*/
      return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }


    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Boolean getEsRefrigerado() {
        return esRefrigerado;
    }

    public void setEsRefrigerado(Boolean esRefrigerado) {
        this.esRefrigerado = esRefrigerado;
    }

    public UnidadDeMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadDeMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}
