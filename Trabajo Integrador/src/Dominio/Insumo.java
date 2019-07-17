package Dominio;

import Dominio.Unidades.UnidadDeMedida;

public abstract class Insumo implements Comparable<Insumo> {

    protected int id;
    protected String descripcion;
    protected Double costo;
    protected Double stock;
    protected Double peso; //en Kg
    protected Boolean esRefrigerado;
    protected UnidadDeMedida unidadMedida;

    public Insumo(){};

    public Insumo(int idInsumo, String descrip, Double costoActual, Double stockActual,
                  Double pesoEnKg, Boolean refrigeracion, UnidadDeMedida unidad){

        this.id = idInsumo;
        this.descripcion = descrip;
        this.costo = costoActual;
        this.stock = stockActual;
        this.peso = pesoEnKg;
        this.esRefrigerado = refrigeracion;
        this.unidadMedida = unidad;

    }

    public int compareTo(Insumo obj){

        return (int) (this.stock - obj.stock);

      /*if(this.stock > obj.stock)    return 1;
      if(this.stock < obj.stock)    return -1;
      return 0;*/
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

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
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
