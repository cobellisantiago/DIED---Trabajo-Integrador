package Dominio;

import java.util.ArrayList;

public class Camion {

    private static ArrayList<Camion> instances = new ArrayList<Camion>();

    private Integer id;
    private String marca;
    private String modelo;
    private Double dominio;
    private Integer anio;
    private Double costoPorKm;
    private Double capacidad;
    private Boolean liquidos;


    public Camion(String marca, String modelo, Double dominio, Integer anio,
                  Double costoPorKm, Double capacidad, Boolean liquidos) {
        this.id = instances.size() + 1;
        this.marca = marca;
        this.modelo = modelo;
        this.dominio = dominio;
        this.anio = anio;
        this.costoPorKm = costoPorKm;
        this.capacidad = capacidad;
        this.liquidos = liquidos;
        instances.add(this);
    }

    public static ArrayList<Camion> getInstances(){
        return instances;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }

    public void setModelo(String modelo) { this.modelo = modelo; }

    public Double getDominio() { return dominio; }

    public void setDominio(Double dominio) { this.dominio = dominio; }

    public Integer getAño() { return anio; }

    public void setAño(Integer año) { this.anio = año; }

    public Double getCostoPorKm() { return costoPorKm; }

    public void setCostoPorKm(Double costoPorKm) { this.costoPorKm = costoPorKm; }

    public Double getCapacidad() { return capacidad; }

    public void setCapacidad(Double capacidad) { this.capacidad = capacidad; }

    public Boolean getLiquidos() { return liquidos; }

    public void setLiquidos(Boolean liquidos) { this.liquidos = liquidos; }


}
