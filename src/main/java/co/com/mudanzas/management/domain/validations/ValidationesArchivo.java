package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.DayOfWorkException;
import co.com.mudanzas.management.exceptions.ValidationsFileException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationesArchivo {

    private DiasTrabajo diasTrabajo;

    public void ejecutar(DetalleDatosCargados detalleDatosCargados) throws DayOfWorkException {

        List<IValidacionesArchivo> iValidacionesArchivo = new ArrayList<>();
        iValidacionesArchivo.add(diasTrabajo);

        try {
            for (IValidacionesArchivo validacionArchivo: iValidacionesArchivo) {
                validacionArchivo.validar(detalleDatosCargados);
            }
        } catch (ValidationsFileException validationsFileException) {
            throw validationsFileException;
        }

    }

}
