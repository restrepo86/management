package co.com.mudanzas.management.domain.model;

import java.util.List;

public class DetalleDatosCargados {

    private int diasATrabajar;
    private List<ElementosDiarios> elementosDiarios;

    public DetalleDatosCargados() {
        super();
    }

    public DetalleDatosCargados(int diasATrabajar, List<ElementosDiarios> elementosDiarios) {
        this.diasATrabajar = diasATrabajar;
        this.elementosDiarios = elementosDiarios;
    }

    public List<ElementosDiarios> getElementosDiarios() {
        return elementosDiarios;
    }

    public void setElementosDiarios(List<ElementosDiarios> elementosDiarios) {
        this.elementosDiarios = elementosDiarios;
    }

    public double getDiasATrabajar() {
        return diasATrabajar;
    }

    public void setDiasATrabajar(int diasATrabajar) {
        this.diasATrabajar = diasATrabajar;
    }


}
