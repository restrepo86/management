package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.DayOfWorkException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class ValidationesArchivoTest {

    @InjectMocks
    private ValidationesArchivo validationesArchivo;

    @Mock
    private DiasTrabajo diasTrabajo;

    private DetalleDatosCargados detalleDatosCargados;

    @BeforeEach
    public void inicializarDatos() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        detalleDatosCargados = podamFactory.manufacturePojoWithFullData(DetalleDatosCargados.class);
    }

    @Test
    public void debeValidarNumeroDeDiasATrabajar() throws DayOfWorkException {
        validationesArchivo.ejecutar(detalleDatosCargados);
        verify(diasTrabajo).validar(detalleDatosCargados);
    }

}