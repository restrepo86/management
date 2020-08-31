package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.contants.Errores;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.DayOfWorkException;

public class DiasTrabajo implements IValidacionesArchivo {

    public void validar(DetalleDatosCargados detalleDatosCargados) throws DayOfWorkException {
        if (detalleDatosCargados.getDiasATrabajar() < 1 || detalleDatosCargados.getDiasATrabajar() > 500) {
            throw new DayOfWorkException(Errores.LOS_DIAS_DE_TRABAJO_DEBEN_SER_MINIMO_1_Y_MAXIMO_QUINIENTOS);
        }
    }

}
