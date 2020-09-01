package co.com.mudanzas.management.domain.ItemsCollector.model;

import java.util.List;

public class DiaTrabajado {

    private List<Bolsa> bolsasEmpacadas;

    public DiaTrabajado(List<Bolsa> bolsasEmpacadas) {
        this.bolsasEmpacadas = bolsasEmpacadas;
    }

    public List<Bolsa> getBolsasEmpacadas() {
        return bolsasEmpacadas;
    }

    public void setBolsasEmpacadas(List<Bolsa> bolsasEmpacadas) {
        this.bolsasEmpacadas = bolsasEmpacadas;
    }

}
