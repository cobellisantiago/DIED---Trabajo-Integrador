package Dominio;

import java.util.ArrayList;
import java.util.Arrays;

public class Camion {

    private static ArrayList<Camion> instances = new ArrayList<Camion>();

    private Integer id;
    private String marca;
    private String modelo;
    private String dominio;
    private Integer anio;
    private Double costoPorKm;
    private Integer capacidad;
    private Boolean liquidos;


    public Camion(String marca, String modelo, String dominio, Integer anio,
                  Double costoPorKm, Integer capacidad, Boolean liquidos) {
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


    public Boolean[] resolver(Double[] peso, Double[] valor) { //Peso de un insumo y valor (costo) de un insumo

        int N = peso.length; // items
        int W = capacidad+1; // max peso

        Double[][] opt = new Double[N][W]; //matriz que guarda el valor de cada esecenario
        /*for(Double[] fila : opt) {
            for(Double d : fila){
                d = 0.0;
            }
        }*/

        Boolean[][] sol = new Boolean[N][W]; // matriz que guarda si el element esta en el esceario

        for (int n = 0; n < N; n++) {
            for (int w = 0; w < W; w++) {
                Double option1 = n < 1 ? 0 : opt[n - 1][w]; //completar
                Double option2 = - Double.MAX_VALUE;
                if (peso[n]<=w) { //Hay espacio en la mochila? if(peso[n] <= w) option2 = valor[n] + opt[n-1][w-weight[n]]
                    option2 = valor[n] + (n != 0 ? opt[n-1][(int)(w-peso[n])]: opt[n][(int)(w-peso[n])]);
                }

                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }
        // determinar la combinación óptima
        Boolean[] esSolucion= new Boolean[N];
        for (int n = N-1, w = W-1; n >= 0; n--) {
            if (sol[n][w]) {
                esSolucion [n] = true;
                // actualizar w
                w =(int)(w-peso[n]);
            } else {
                esSolucion [n] = false;
            }
        }
        System.out.println("Pares peso valor en solucion optima");
        Boolean b=false;
        for(int i=0;i<N;i++){

            if(esSolucion[i]){
                if(b) System.out.print(" - ");
                System.out.print("("+peso[i]+" "+valor[i]+")");
                b=true;
            }

        }
        System.out.println("\n");
        return esSolucion;

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

    public String getDominio() { return dominio; }

    public void setDominio(String dominio) { this.dominio = dominio; }

    public Integer getAnio() { return anio; }

    public void setAnio(Integer anio) { this.anio = anio; }

    public Double getCostoPorKm() { return costoPorKm; }

    public void setCostoPorKm(Double costoPorKm) { this.costoPorKm = costoPorKm; }

    public Integer getCapacidad() { return capacidad; }

    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public Boolean getLiquidos() { return liquidos; }

    public void setLiquidos(Boolean liquidos) { this.liquidos = liquidos; }


}
