package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.ValidationsFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationesArchivo {

    private static List<IValidacionesArchivo> iValidacionesArchivos;

    @Autowired
    private DiasTrabajo diasTrabajo;

    @Autowired
    private CantidadElementosDia cantidadElementosDia;

    @Autowired
    private PesoElementos pesoElementos;

    public void ejecutar(DetalleDatosCargados detalleDatosCargados) throws ValidationsFileException {

        List<IValidacionesArchivo> iValidacionesArchivo = construirValidadores();

        try {
            for (IValidacionesArchivo validacionArchivo: iValidacionesArchivo) {
                validacionArchivo.validar(detalleDatosCargados);
            }
        } catch (ValidationsFileException validationsFileException) {
            throw validationsFileException;
        }

    }

    private List<IValidacionesArchivo> construirValidadores() {
        if (iValidacionesArchivos == null) {
            iValidacionesArchivos = new ArrayList<>();
            iValidacionesArchivos.add(diasTrabajo);
            iValidacionesArchivos.add(cantidadElementosDia);
            iValidacionesArchivos.add(pesoElementos);
        }
        return iValidacionesArchivos;
    }

}
