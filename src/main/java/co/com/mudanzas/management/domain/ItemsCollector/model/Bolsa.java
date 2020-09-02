package co.com.mudanzas.management.domain.ItemsCollector.model;

import co.com.mudanzas.management.domain.model.Elemento;

import java.util.List;

public class Bolsa {

    private List<Elemento> elementos;

    public Bolsa(List<Elemento> elementos) {
        this.elementos = elementos;
    }

    public List<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }

}
