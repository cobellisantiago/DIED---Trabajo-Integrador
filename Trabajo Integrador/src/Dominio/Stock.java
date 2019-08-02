package Dominio;

import java.util.ArrayList;

import Gestores.GestorInsumos;

public class Stock {
	
	private static ArrayList<Stock> instances = new ArrayList<Stock>();
    private Integer id;
    private Double cantidad;
    private Integer puntoPedido;
    private Insumo insumo;
    private Planta planta;

    @SuppressWarnings("unused")
	private Stock() {}
    
    public Stock(Double cant, Integer puntoPed, Insumo ins, Planta plant) {
    	this.id = instances.size() + 1;
    	this.cantidad = cant;
    	this.puntoPedido = puntoPed;
    	this.insumo = ins;
    	this.planta = plant;
    	instances.add(this);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPuntoPedido() {
        return puntoPedido;
    }

    public void setPuntoPedido(Integer puntoPedido) {
        this.puntoPedido = puntoPedido;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
    
    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
    
    public static ArrayList<Stock> getInstances(){
    	return instances;
    }
}
