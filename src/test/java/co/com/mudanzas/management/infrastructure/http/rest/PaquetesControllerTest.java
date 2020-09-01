package co.com.mudanzas.management.infrastructure.http.rest;

import co.com.mudanzas.management.domain.model.DatosEntrada;
import co.com.mudanzas.management.domain.services.PaquetesService;
import co.com.mudanzas.management.exceptions.ManagementException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class PaquetesControllerTest {

    private static final String CEDULA_PARTICIPANTE = "328144771";

    @InjectMocks
    private PaquetesController paquetesController;

    @Mock
    private PaquetesService paquetesService;

    private MultipartFile archivoDetalleTrabajo;

    @Test
    public void debePermitirCargarDetalleDeTrabajoDiarioYDividirloEnPaquetes() throws ManagementException {
        paquetesController.almacenarEnBolsas(archivoDetalleTrabajo, CEDULA_PARTICIPANTE);
        Mockito.verify(paquetesService).almacenarEnBolsas(Mockito.any(DatosEntrada.class));
    }

}
