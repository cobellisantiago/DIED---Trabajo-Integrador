package Dominio;

import Dominio.Unidades.UnidadDeMedida;
import Gestores.GestorInsumos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Insumo implements Comparable<Insumo> {

    private static ArrayList<Insumo> instances = new ArrayList<Insumo>();
    protected Integer id;
    protected String descripcion;
    protected Double costo;
    protected Double stockTotal;
    protected Double peso; //en Kg
    protected Boolean esRefrigerado;
    protected UnidadDeMedida unidadMedida;


    public Insumo (Double c, Double sT, String s) {
        this.costo = c;
        this.stockTotal = sT;
        this.descripcion = s;
    }

    public Insumo(String descrip, Double costoActual, Double pesoEnKg, Boolean refrigeracion){
        this.id = instances.size() + 1;
        this.descripcion = descrip;
        this.costo = costoActual;
        this.peso = pesoEnKg;
        this.esRefrigerado = refrigeracion;
        this.stockTotal = new Double(0);

        instances.add(this);
    }

    @SuppressWarnings("unused")
    private Insumo(){
        this.id = instances.size() + 1;
        instances.add(this);
    };

    public static ArrayList<Insumo> getInstances(){
        return instances;
    }

    public void borrar(){
        instances.remove(this);
    }

    public void editar(String newName,Double newCosto, Double newPesoEnKg, Boolean newRefrigeracion ){
        this.descripcion = newName ;
        this.costo = newCosto ;
        this.peso= newPesoEnKg ;
        this.esRefrigerado= newRefrigeracion ;
    }

    public int compareTo(Insumo obj){

        if(this.costo > obj.costo)    return 1;
        if(this.costo < obj.costo)    return -1;
        return 0;
    }

    public Integer getId() {
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

    public Double getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Double stock) {
        this.stockTotal = stock;
    }
    
    public UnidadDeMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadDeMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public static Comparator<Insumo> comparadorNombre = new Comparator<Insumo>(){
        public int compare(Insumo i1, Insumo i2) {
            return i1.descripcion.compareTo(i2.descripcion);
        }
    };

    public static Comparator<Insumo> comparadorCosto = new Comparator<Insumo>(){
        public int compare(Insumo i1, Insumo i2) {
            return i1.costo.compareTo(i2.costo);
        }
    };

    public static Comparator<Insumo> comparadorStock = new Comparator<Insumo>(){
        public int compare(Insumo i1, Insumo i2) {
            return i1.stockTotal.compareTo(i2.stockTotal);
        }
    };

}