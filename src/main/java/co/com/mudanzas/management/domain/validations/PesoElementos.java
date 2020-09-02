package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.contants.Errores;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.model.Elemento;
import co.com.mudanzas.management.domain.model.ElementosDiarios;
import co.com.mudanzas.management.exceptions.InvalidWeightException;
import co.com.mudanzas.management.exceptions.ValidationsFileException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PesoElementos implements IValidacionesArchivo {

    @Override
    public void validar(DetalleDatosCargados detalleDatosCargados) throws ValidationsFileException {
        List<List<Elemento>> elementos =
                detalleDatosCargados.getElementosDiarios()
                        .stream().map(ElementosDiarios::getElementos).collect(Collectors.toList());

        if (tienePesosFueraDelRangoPermitido(elementos)) {
            throw new InvalidWeightException(Errores.EL_PESO_DE_CADA_UNO_DE_LOS_ELEMENTOS_DEBE_SER_MINIMO_UNO_Y_MAXIMO_CIEN);
        } else if (sumatoriaPesosDiariosEsMenorACincuentaLibras(elementos)){
            throw new InvalidWeightException(Errores.LA_SUMA_DE_LOS_PESOS_POR_DIA_NO_PUEDE_SER_MENOR_A_CINCUENTA_LIBRAS);
        }
    }

    private boolean tienePesosFueraDelRangoPermitido(List<List<Elemento>> elementos) {
        return elementos.stream()
                .map(elementosDia -> elementosDia
                        .stream()
                        .filter(elemento -> elemento.getPeso() < 1 || elemento.getPeso() > 100)
                        .findAny()
                        .isPresent()
                )
                .anyMatch(Boolean::booleanValue);
    }

    private boolean sumatoriaPesosDiariosEsMenorACincuentaLibras(List<List<Elemento>> elementos) {
        return elementos.stream()
                .map(elementosDia -> elementosDia
                        .stream()
                        .mapToDouble(Elemento::getPeso)
                        .sum()
                )
                .filter(sumaPesosDia -> sumaPesosDia.doubleValue() < 50d)
                .findAny()
                .isPresent();
    }

}
