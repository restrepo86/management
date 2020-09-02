package co.com.mudanzas.management.domain.model;

import java.util.List;

public class ElementosDiarios {

    private int cantidad;
    private List<Elemento> elementos;

    public ElementosDiarios(int cantidad, List<Elemento> elementos) {
        this.cantidad = cantidad;
        this.elementos = elementos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }

}
