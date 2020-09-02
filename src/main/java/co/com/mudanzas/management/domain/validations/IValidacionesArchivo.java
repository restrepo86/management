package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.ValidationsFileException;

public interface IValidacionesArchivo {

    void validar(DetalleDatosCargados detalleDatosCargados) throws ValidationsFileException;

}
