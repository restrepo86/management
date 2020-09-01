package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.DayOfWorkException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DiasTrabajoTest {

    @InjectMocks
    private DiasTrabajo diasTrabajo;

    private DetalleDatosCargados detalleDatosCargados;

    @BeforeEach
    public void inicializarDatos() {
        detalleDatosCargados = new DetalleDatosCargados();
    }

    @Test
    public void debeValidarQueNumeroDeDiasATrabajarSeaMayorOIgualQueUnoYMenorOIgualQueQuinientos() throws DayOfWorkException {
        detalleDatosCargados.setDiasATrabajar(3);
        assertDoesNotThrow(() -> diasTrabajo.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueNumeroDeDiasATrabajarSeaMayorOIgualQueUnoYMenorOIgualQueQuinientosCasoDos() throws DayOfWorkException {
        detalleDatosCargados.setDiasATrabajar(1);
        assertDoesNotThrow(() -> diasTrabajo.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueNumeroDeDiasATrabajarSeaMayorOIgualQueUnoYMenorOIgualQueQuinientosCasoTres() throws DayOfWorkException {
        detalleDatosCargados.setDiasATrabajar(500);
        assertDoesNotThrow(() -> diasTrabajo.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueNumeroDeDiasATrabajarSeaMayorOIgualQueUnoYMenorOIgualQueQuinientosYRetornarException() {
        detalleDatosCargados.setDiasATrabajar(501);
        assertThrows(DayOfWorkException.class, () -> diasTrabajo.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueNumeroDeDiasATrabajarSeaMayorOIgualQueUnoYMenorOIgualQueQuinientosYRetornarExceptionCasoDos() {
        detalleDatosCargados.setDiasATrabajar(-2);
        assertThrows(DayOfWorkException.class, () -> diasTrabajo.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueNumeroDeDiasATrabajarSeaMayorOIgualQueUnoYMenorOIgualQueQuinientosYRetornarExceptionCasoTres() {
        detalleDatosCargados.setDiasATrabajar(0);
        assertThrows(DayOfWorkException.class, () -> diasTrabajo.validar(detalleDatosCargados));
    }

}
