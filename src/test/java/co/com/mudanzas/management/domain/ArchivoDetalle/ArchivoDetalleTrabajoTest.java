package co.com.mudanzas.management.domain.ArchivoDetalle;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
public class ArchivoDetalleTrabajoTest {

    private static final Logger LOG = LoggerFactory.getLogger(ArchivoDetalleTrabajo.class);
    private static final String LAZY_LOADING_TXT = "lazy_loading.txt";
    private static final String SRC_TEST_RESOURCES_ARCHIVO_DETALLE_TRABAJO = "src/test/resources/archivo/detalle/trabajo/";
    private static final String ERROR_LEYENDO_EL_ARCHIVO = "Error leyendo el archivo";

    @InjectMocks
    private ArchivoDetalleTrabajo archivoDetalleTrabajo;

    private MultipartFile archivoDetalleEntrada;

    @BeforeEach
    public void inicializarDatos() {
        Path fileName = Paths.get(SRC_TEST_RESOURCES_ARCHIVO_DETALLE_TRABAJO.concat(LAZY_LOADING_TXT));
        File archivoEntrada = new File(fileName.toUri());
        try {
            byte[] bytesArchivo = Files.readAllBytes(archivoEntrada.toPath());
            archivoDetalleEntrada = new MockMultipartFile(LAZY_LOADING_TXT, bytesArchivo);
        } catch (IOException e) {
            LOG.error(ERROR_LEYENDO_EL_ARCHIVO);
        }

    }

    @Test
    public void debeLeerArchivoPlanoEInterpretarElPrimerCampoComoElNumeroDeDiasQueTrabajaraElEmpleado() throws InvalidFileException {
        DetalleDatosCargados detalleDatosCargados = archivoDetalleTrabajo.cargar(archivoDetalleEntrada);
        assertEquals(5d, detalleDatosCargados.getDiasATrabajar());
    }

    @Test
    public void debeLeerArchivoPlanoEInterpretarElPrimerCampoComoElNumeroDeDiasQueTrabajaraElEmpleadoCasoDos() throws InvalidFileException {
        DetalleDatosCargados detalleDatosCargados = archivoDetalleTrabajo.cargar(archivoDetalleEntrada);
        assertNotEquals(1d, detalleDatosCargados.getDiasATrabajar());
    }

    @Test
    public void debeObtenerElNumeroDeElementosASerMovidosConSusRespectivosPesos() throws InvalidFileException {
        DetalleDatosCargados detalleDatosCargados = archivoDetalleTrabajo.cargar(archivoDetalleEntrada);
        assertEquals(4, detalleDatosCargados.getElementosDiarios().get(0).getCantidad());
        assertEquals(4, detalleDatosCargados.getElementosDiarios().get(0).getElementos().size());
        assertEquals(3, detalleDatosCargados.getElementosDiarios().get(1).getCantidad());
        assertEquals(3, detalleDatosCargados.getElementosDiarios().get(1).getElementos().size());
        assertEquals(11, detalleDatosCargados.getElementosDiarios().get(2).getCantidad());
        assertEquals(11, detalleDatosCargados.getElementosDiarios().get(2).getElementos().size());
        assertEquals(6, detalleDatosCargados.getElementosDiarios().get(3).getCantidad());
        assertEquals(6, detalleDatosCargados.getElementosDiarios().get(3).getElementos().size());
        assertEquals(10, detalleDatosCargados.getElementosDiarios().get(4).getCantidad());
        assertEquals(10, detalleDatosCargados.getElementosDiarios().get(4).getElementos().size());
    }

    @Test
    public void debeObtenerElNumeroDeElementosASerMovidosConSusRespectivosPesosCasoDos() throws InvalidFileException {
        DetalleDatosCargados detalleDatosCargados = archivoDetalleTrabajo.cargar(archivoDetalleEntrada);
        assertNotEquals(5, detalleDatosCargados.getElementosDiarios().get(0).getCantidad());
        assertNotEquals(2, detalleDatosCargados.getElementosDiarios().get(1).getCantidad());
        assertNotEquals(1, detalleDatosCargados.getElementosDiarios().get(1).getElementos().size());
        assertNotEquals(15, detalleDatosCargados.getElementosDiarios().get(2).getCantidad());
        assertNotEquals(12, detalleDatosCargados.getElementosDiarios().get(2).getElementos().size());
        assertNotEquals(8, detalleDatosCargados.getElementosDiarios().get(3).getCantidad());
        assertNotEquals(9, detalleDatosCargados.getElementosDiarios().get(3).getElementos().size());
        assertNotEquals(11, detalleDatosCargados.getElementosDiarios().get(4).getCantidad());
        assertNotEquals(9, detalleDatosCargados.getElementosDiarios().get(4).getElementos().size());
    }

}
