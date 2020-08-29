package co.com.mudanzas.management.domain.services;

import co.com.mudanzas.management.domain.ArchivoDetalle.ArchivoDetalleTrabajo;
import co.com.mudanzas.management.domain.Paquetes.Paquetes;
import co.com.mudanzas.management.domain.validations.ValidationesArchivo;
import co.com.mudanzas.management.infraestructura.data.services.DetalleEmpaqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
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

    private File archivoDetalle;
    private List<Double> valoresArchivo;
    private List<String> salidaPaquetesPorDia;

    @BeforeEach
    public void inicializarDatos() {
        valoresArchivo = Arrays.asList(1d, 2d, 6d);
        salidaPaquetesPorDia = Arrays.asList("Case #1: 2", "Case #2: 3", "Case #3: 4");
    }

    @Test
    public void debeAlmacenarPaquetesEnBolsasYCargarArchivo() {
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(archivoDetalleTrabajo).cargar(archivoDetalle);
    }

    @Test
    public void debeValidarDatosArchivo() {
        when(archivoDetalleTrabajo.cargar(archivoDetalle)).thenReturn(valoresArchivo);
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(validacionesArchivo).ejecutar(valoresArchivo);
    }

    @Test
    public void debeEmpacarElementosEnBolsas() {
        when(archivoDetalleTrabajo.cargar(archivoDetalle)).thenReturn(valoresArchivo);
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(paquetes).almacenar(valoresArchivo);
    }

    @Test
    public void debeDejarTrazaDelProcesoDeEmpaqueEnBaseDeDatos() {
        when(archivoDetalleTrabajo.cargar(archivoDetalle)).thenReturn(valoresArchivo);
        when(paquetes.almacenar(valoresArchivo)).thenReturn(salidaPaquetesPorDia);
        paquetesService.almacenarEnBolsas(archivoDetalle);
        verify(detalleEmpaqueService).guardar(salidaPaquetesPorDia);
    }

}
