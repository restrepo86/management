package co.com.mudanzas.management.domain.ItemsCollector.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DiasTrabajados {

    private List<DiaTrabajado> detalle;

    public DiasTrabajados(List<DiaTrabajado> detalle) {
        this.detalle = detalle;
    }

    public List<DiaTrabajado> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DiaTrabajado> detalle) {
        this.detalle = detalle;
    }

    public List<String> obtenerInforme() {
        AtomicInteger indice = new AtomicInteger();
        return detalle
                .stream()
                .map(diaTrabajado -> new StringBuilder()
                        .append("Case #")
                        .append(indice.incrementAndGet())
                        .append(": ")
                        .append(diaTrabajado.getBolsasEmpacadas().size()).toString())
                .collect(Collectors.toList());
    }
}
