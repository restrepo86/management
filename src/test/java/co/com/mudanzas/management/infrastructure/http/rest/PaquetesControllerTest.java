package co.com.mudanzas.management.infrastructure.http.rest;

import co.com.mudanzas.management.domain.services.PaquetesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class PaquetesControllerTest {

    @InjectMocks
    private PaquetesController paquetesController;

    @Mock
    private PaquetesService paquetesService;

    private File archivoDetalleTrabajo;


    @Test
    public void debePermitirCargarDetalleDeTrabajoDiarioYDividirloEnPaquetes() {
        paquetesController.almacenarEnBolsas(archivoDetalleTrabajo);
        Mockito.verify(paquetesService).almacenarEnBolsas(archivoDetalleTrabajo);
    }

}
