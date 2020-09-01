package co.com.mudanzas.management.domain.Paquetes;

import co.com.mudanzas.management.domain.ItemsCollector.BolsaCollector;
import co.com.mudanzas.management.domain.ItemsCollector.model.DiasTrabajados;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.model.ElementosDiarios;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

@Service
public class Paquetes {

    public List<String> almacenar(DetalleDatosCargados detalleDatosCargados) {

        BolsaCollector bolsaCollector = new BolsaCollector();
        Set<Collector.Characteristics> characteristics = bolsaCollector.characteristics();
        DiasTrabajados diasTrabajados =
                detalleDatosCargados.getElementosDiarios()
                        .stream()
                        .map(ElementosDiarios::getElementos)
                        .collect(Collector.of(
                            bolsaCollector.supplier(),
                            bolsaCollector.accumulator(),
                            bolsaCollector.combiner(),
                            bolsaCollector.finisher(),
                            characteristics.toArray(new Collector.Characteristics[characteristics.size()])
                ));

        return diasTrabajados.obtenerInforme();

    }

}
