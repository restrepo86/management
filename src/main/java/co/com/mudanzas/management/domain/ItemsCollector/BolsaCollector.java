package co.com.mudanzas.management.domain.ItemsCollector;

import co.com.mudanzas.management.domain.ItemsCollector.model.DiasTrabajados;
import co.com.mudanzas.management.domain.model.Elemento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BolsaCollector implements Collector<List<Elemento>, BolsaAcomulador, DiasTrabajados> {

    @Override
    public Supplier<BolsaAcomulador> supplier() {
        return () -> new BolsaAcomulador(new ArrayList<>());
    }

    @Override
    public BiConsumer<BolsaAcomulador, List<Elemento>> accumulator() {
        return BolsaAcomulador::acomular;
    }

    @Override
    public BinaryOperator<BolsaAcomulador> combiner() {
        return BolsaAcomulador::combinar;
    }

    @Override
    public Function<BolsaAcomulador, DiasTrabajados> finisher() {
        return BolsaAcomulador::finalizar;
    }

    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> chars = new HashSet<Collector.Characteristics>();
        chars.add(Characteristics.CONCURRENT);
        return chars;
    }

}
