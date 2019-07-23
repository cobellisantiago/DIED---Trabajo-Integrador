package Dominio;

import Estructuras.Arista;

public class Ruta extends Arista {

    private Double distancia; //Medida en Km
    private Integer duracion; //Medido en minutos
    private Double pesoMaximo; //Medido en toneladas


    public Ruta(Planta inicio, Planta fin,Double dist, Integer minutos, Double pesoSoportado){

        super(inicio,fin);
        distancia = dist;
        duracion = minutos;
        pesoMaximo = pesoSoportado;

    }


}
