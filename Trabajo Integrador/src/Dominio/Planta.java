package Dominio;

import Estructuras.Vertice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Planta extends Vertice {

    private List<Stock> listaStocks;
    private Integer id;
    private String nombre;


    public Double costoTotal() {
        Double costoTotal;
        costoTotal = listaStocks.stream().mapToDouble((s) -> s.getCantidad() * s.getInsumo().getCosto()).sum();

        /* usando el API de STREAM calcular el costo total de la suma de todos los productos
         que se encuentran en stock multiplicando el costo (por unidad)
         y la cantidad de unidades usar los métodos mapToDouble y sum */

        return costoTotal;
    }

    public List<Insumo> stockEntre(Integer s1, Integer s2) {
        List<Insumo> listaInsumos = new ArrayList<>();

        listaInsumos = listaStocks.stream().filter((s) -> s1>=s.getCantidad()
                && s.getCantidad()<=s2).map((s) -> s.getInsumo()).collect(Collectors.toList());

        /* usando el API de STREAM retornar una lista de todos
           los insumos cuyo stock se encuentra entre S1 y S2
           puede utilizar los métodos filter y Collector.toList */

        return listaInsumos;
    }

    public Boolean necesitaInsumo(Insumo i) {
        boolean necesitaStock;
        necesitaStock = listaStocks.stream().anyMatch((s) -> s.getInsumo().equals(i) && s.getCantidad()<=s.getPuntoPedido());
        // retorna TRUE si el insumo i se encuentra con un stock menor
        // al punto de pedido de esa planta.

        return necesitaStock;
    }

    public List<Stock> getListaStocks() {
        return listaStocks;
    }

    public void setListaStocks(List<Stock> listaStocks) {
        this.listaStocks = listaStocks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}