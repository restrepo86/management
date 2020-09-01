package co.com.mudanzas.management.domain.services;

import co.com.mudanzas.management.domain.ArchivoDetalle.ArchivoDetalleTrabajo;
import co.com.mudanzas.management.domain.Paquetes.Paquetes;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.validations.ValidationesArchivo;
import co.com.mudanzas.management.exceptions.ManagementException;
import co.com.mudanzas.management.infrastructure.data.services.DetalleEmpaqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class PaquetesServiceTest {

    @InjectMocks
    private PaquetesService paquetesService;

    @Mock
    private ArchivoDetalleTrabajo archivoDetalleTrabajo;

    @Mock
    private ValidationesArchivo validacionesArchivo;

    @Mock
    private Paquetes paquetes;

    @Mock
    private DetalleEmpaqueService detalleEmpaqueService;

    private MultipartFile archivoDetalle;
    private List<Double> valoresArchivo;
    private List<String> salidaPaquetesPorDia;
    private DetalleDatosCargados detalleDatosCargados;

    @BeforeEach
    public void inicializarDatos() {
        valoresArchivo = Arrays.asList(1d, 2d, 6d);
        salidaPaquetesPorDia = Arrays.asList("Case #1: 2", "Case #2: 3", "Case #3: 4");

        detalleDatosCargados = new DetalleDatosCargados();
        detalleDatosCargados.setElementosDiarios(new ArrayList<>());
    }

    @Test
    public void debeAlmacenarPaquetesEnBolsasYCargarArchivo() throws ManagementException {
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(archivoDetalleTrabajo).cargar(archivoDetalle);
    }

    @Test
    public void debeValidarDatosArchivo() throws ManagementException {
        when(archivoDetalleTrabajo.cargar(archivoDetalle)).thenReturn(detalleDatosCargados);
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(validacionesArchivo).ejecutar(detalleDatosCargados);
    }

    @Test
    public void debeEmpacarElementosEnBolsas() throws ManagementException {
        when(archivoDetalleTrabajo.cargar(archivoDetalle)).thenReturn(detalleDatosCargados);
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(paquetes).almacenar(detalleDatosCargados);
    }

    @Test
    public void debeDejarTrazaDelProcesoDeEmpaqueEnBaseDeDatos() throws ManagementException {
        when(archivoDetalleTrabajo.cargar(archivoDetalle)).thenReturn(detalleDatosCargados);
        when(paquetes.almacenar(detalleDatosCargados)).thenReturn(salidaPaquetesPorDia);
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(detalleEmpaqueService).guardar(salidaPaquetesPorDia);
    }

}
