package co.com.mudanzas.management.domain.ItemsCollector;

import co.com.mudanzas.management.domain.ItemsCollector.model.Bolsa;
import co.com.mudanzas.management.domain.ItemsCollector.model.DiaTrabajado;
import co.com.mudanzas.management.domain.ItemsCollector.model.DiasTrabajados;
import co.com.mudanzas.management.domain.model.Elemento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BolsaAcomulador {

    private List<DiaTrabajado> diasTrabajados;

    public BolsaAcomulador(List<DiaTrabajado> diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public static void acomular(BolsaAcomulador bolsaAcomulador, List<Elemento> elementos) {
        List<Double> pesosItems =
                elementos
                        .parallelStream()
                        .map(Elemento::getPeso)
                        .collect(Collectors.toList());

        Double pesoMaximo = Collections.max(pesosItems);
        int cantidadDeElementos = pesosItems.size();
        List<List<Double>> bolsas = new ArrayList<>();
        for (int indice = 1; indice <= cantidadDeElementos + 1; indice ++) {
            List<Double> bolsa = new ArrayList<>();
            if (pesoMaximo * indice >= 50) {
                bolsa.add(pesoMaximo);
                pesosItems.remove(pesoMaximo);
                if (pesosItems.size() > 0) {
                    agregarPesosMenoresACincuenta(pesosItems, pesoMaximo, indice, bolsa);
                    if (pesosItems.size() > 0) {
                        pesoMaximo = Collections.max(pesosItems);
                    }
                    indice = 1;
                }
                cantidadDeElementos = pesosItems.size();
            }
            agregarBolsa(bolsas, bolsa);
        }

        List<Bolsa> bolsasPorDia = bolsas.parallelStream().map(dato -> new Bolsa(dato.stream().map(Elemento::new).collect(Collectors.toList()))).collect(Collectors.toList());
        bolsaAcomulador.diasTrabajados.add(new DiaTrabajado(bolsasPorDia));
    }

    private static void agregarPesosMenoresACincuenta(List<Double> pesosItems, Double pesoMaximo, int indice, List<Double> bolsa) {
        if (pesoMaximo <= 50 && indice > 1) {
            for (int i = 1; i < indice; i++) {
                Double pesoMinimo = Collections.min(pesosItems);
                bolsa.add(pesoMinimo);
                pesosItems.remove(pesoMinimo);
                if (pesosItems.size() == 1 && pesosItems.get(0) <= 50) {
                    bolsa.add(pesosItems.get(0));
                    pesosItems.clear();
                    break;
                }
            }
        }
    }


    private static void agregarBolsa(List<List<Double>> bolsas, List<Double> bolsa) {
        if (!bolsa.isEmpty()) {
            bolsas.add(bolsa);
        }
    }


    public static BolsaAcomulador combinar(BolsaAcomulador bolsaAcomulador, BolsaAcomulador bolsaAcomulador1) {
        return null;
    }

    public static DiasTrabajados finalizar(BolsaAcomulador bolsaAcomulador) {
        return new DiasTrabajados(bolsaAcomulador.diasTrabajados);
    }

}
