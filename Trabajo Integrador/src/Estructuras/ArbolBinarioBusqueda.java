package Estructuras;

import java.util.ArrayList;
import java.util.List;

import Dominio.Insumo;

public class ArbolBinarioBusqueda<E extends Comparable<E>> extends Arbol<E>{

    protected Arbol<E> izquierdo;
    protected Arbol<E> derecho;

    public ArbolBinarioBusqueda(){
        this.valor=null;
        this.izquierdo=new ArbolVacio<E>();
        this.derecho=new ArbolVacio<E>();
    }

    public ArbolBinarioBusqueda(E e){
        this.valor=e;
        this.izquierdo=new ArbolVacio<E>();
        this.derecho=new ArbolVacio<E>();
    }

    public ArbolBinarioBusqueda(E e,Arbol<E> i,Arbol<E> d){
        this.valor=e;
        this.izquierdo=i;
        this.derecho=d;
    }

    @Override
    public List<E> preOrden() {
        List<E> lista = new ArrayList<E>();
        lista.add(this.valor);
        lista.addAll(this.izquierdo.preOrden());
        lista.addAll(this.derecho.preOrden());
        return lista;
    }
    @Override
    public List<E> inOrden() {
        List<E> lista = new ArrayList<E>();
        lista.addAll(this.izquierdo.preOrden());
        lista.add(this.valor);
        lista.addAll(this.derecho.preOrden());
        return lista;
    }
    @Override
    public List<E> posOrden() {
        List<E> lista = new ArrayList<E>();
        lista.addAll(this.izquierdo.preOrden());
        lista.addAll(this.derecho.preOrden());
        lista.add(this.valor);
        return lista;

    }
    @Override
    public boolean esVacio() {
        return false;
    }

    @Override
    public E valor() {
        return this.valor;
    }

    @Override
    public Arbol<E> izquierdo() {
        return this.izquierdo;
    }

    @Override
    public Arbol<E> derecho() {
        return this.derecho;
    }

    @Override
    public void agregar(E a) {
    	
    }

    @Override
    public void agregarCosto(E a) {
    	
    	if(Insumo.comparadorCosto.compare((Insumo)this.valor, (Insumo)a) > 0) {
    		if (this.derecho.esVacio()) this.derecho = new ArbolBinarioBusqueda<E>(a);
            else this.derecho.agregarCosto(a);
    	}
        /*if(this.valor.compareTo(a)<1) {
            if (this.derecho.esVacio()) this.derecho = new ArbolBinarioBusqueda<E>(a);
            else this.derecho.agregar(a);
        }*/
    	else {
    		if (this.izquierdo.esVacio()) this.izquierdo= new ArbolBinarioBusqueda<E>(a);
            else this.izquierdo.agregarCosto(a);
    	}
    	/*else {
            if (this.izquierdo.esVacio()) this.izquierdo= new ArbolBinarioBusqueda<E>(a);
            else this.izquierdo.agregar(a);
        }*/
    }

    @Override
    public boolean equals(Arbol<E> unArbol) {
        return this.valor.equals(unArbol.valor()) && this.izquierdo.equals(unArbol.izquierdo()) && this.derecho.equals(unArbol.derecho());
    }

    @Override
    public boolean contiene(E unValor) {
        if(this.valor == unValor) return true;
        if(this.izquierdo.contiene(unValor)) return true;
        return this.derecho.contiene(unValor);
        // TODO 1.a

    }

    @Override
    public int profundidad() {
        // TODO 1.b
        if(this.derecho.esVacio() && this.izquierdo.esVacio()) return 0;

        int profIzq, profDcho;
        profDcho=this.derecho.profundidad();
        profIzq=this.izquierdo.profundidad();

        if(profDcho<profIzq) return profIzq+1;
        else return profDcho+1;

    }

    @Override
    public int cuentaNodosDeNivel(int nivel) {
        // TODO 1.c
        if(nivel==0) return 1;
        return (this.izquierdo.cuentaNodosDeNivel(nivel-1)+this.derecho.cuentaNodosDeNivel(nivel-1));

    }

    @Override
    public boolean esCompleto() {
        // TODO 1.d
        if(this.izquierdo.esVacio() && this.derecho.esVacio()) return true;

        int profIzq, profDcho;
        profDcho=this.derecho.profundidad();
        profIzq=this.izquierdo.profundidad();
        if(profDcho == profIzq) {
            if(this.derecho.esVacio()){
                return this.izquierdo.esLleno();
            }
            //return (this.izquierdo.esLleno() && this.derecho.esCompleto());
            return (this.derecho.esLleno() && this.izquierdo.esCompleto());
        }else if(profDcho==profIzq-1){
            if(profIzq>=1) return (this.izquierdo.esCompleto() && this.derecho.esLleno());
            return(this.izquierdo.esCompleto() && this.derecho.esVacio());
        }

        return false;
    }

    @Override
    public boolean esLleno() {
        // TODO 1.e

        //double cantNodosMaxima = Math.pow(2,this.profundidad()-1);
        int profundidad = this.profundidad();

        //int sumaNodos=0;
		/*for(int i=0;i<=this.profundidad();i++){
			sumaNodos+=this.cuentaNodosDeNivel(i);
		}*/

        return (Math.pow(2,this.profundidad()) == this.cuentaNodosDeNivel(profundidad));
    }
    
    @Override
    public List<E> rango(E inicio, E fin){
    	return new ArrayList<E>();
    }
    
    @Override
    public List<E> rangoCosto(E inicio, E fin){
        List<E> lista = new ArrayList<E>();
        
        if(Insumo.comparadorCosto.compare((Insumo)this.valor, (Insumo)fin) > 0) {
        	lista.addAll(this.izquierdo().rangoCosto(inicio, fin));
            return lista;
        }
        /*if(this.valor.compareTo(fin) > 0) {
            lista.addAll(this.izquierdo().rango(inicio, fin));
            return lista;
        }*/
        if(Insumo.comparadorCosto.compare((Insumo)this.valor, (Insumo)inicio) < 0) {
        	lista.addAll(this.derecho().rangoCosto(inicio, fin));
            return lista;
        }
        /*if(this.valor.compareTo(inicio) < 0){
            lista.addAll(this.derecho().rango(inicio, fin));
            return lista;
        }*/

        lista.add(this.valor);

        lista.addAll(this.izquierdo().rangoCosto(inicio, fin));
        lista.addAll(this.derecho().rangoCosto(inicio, fin));

        return lista;
    }
    
    @Override
    public List<E> rangoStock(E inicio, E fin){
    	return new ArrayList<E>();
    }
}
