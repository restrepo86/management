package co.com.mudanzas.management.domain.ArchivoDetalle;

import co.com.mudanzas.management.domain.contants.Errores;
import co.com.mudanzas.management.domain.contants.Parametros;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.model.Elemento;
import co.com.mudanzas.management.domain.model.ElementosDiarios;
import co.com.mudanzas.management.exceptions.InvalidFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class ArchivoDetalleTrabajo {

    private static Logger LOG = LoggerFactory.getLogger(ArchivoDetalleTrabajo.class);

    public DetalleDatosCargados cargar(MultipartFile archivoDetalleTrabajo) throws InvalidFileException {
        DetalleDatosCargados detalleDatosCargados = new DetalleDatosCargados();
        Path archivoTemporal = obtenerPathDeArchivo(archivoDetalleTrabajo);
        try (Stream<String> fileLines = Files.lines(archivoTemporal, UTF_8)) {

            List<String> lineasArchivo = fileLines.collect(Collectors.toList());
            detalleDatosCargados.setDiasATrabajar(lineasArchivo
                    .stream()
                    .findFirst()
                    .map(Integer::new)
                    .orElseThrow(() -> new InvalidFileException(Errores.EL_ARCHIVO_NO_PUEDE_ESTAR_VACIO)));
            detalleDatosCargados.setElementosDiarios(obtenerElementosPorDia(lineasArchivo));

        } catch (IOException ioException) {
            LOG.error(Errores.NO_SE_PUDO_CARGAR_EL_ARCHIVO_CON_DETALLE_DE_TRABAJO_POR, ioException);
            throw new InvalidFileException(ioException.getMessage());
        } catch (IndexOutOfBoundsException indexOutBoundsException) {
            LOG.error(Errores.NO_SE_PUDO_CARGAR_EL_ARCHIVO_CON_DETALLE_DE_TRABAJO_POR, indexOutBoundsException);
            throw new InvalidFileException(Errores.EL_ARCHIVO_NO_CONTIENE_EL_NUMERO_DE_LINEAS_CORRECTO);
        } catch (Exception exception) {
            LOG.error(Errores.NO_SE_PUDO_CARGAR_EL_ARCHIVO_CON_DETALLE_DE_TRABAJO_POR, exception);
            throw new InvalidFileException(Errores.EL_ARCHIVO_CONTIENE_UN_FORMATO_INCORRECTO);
        }
        return detalleDatosCargados;

    }

    private Path obtenerPathDeArchivo(MultipartFile archivoDetalleTrabajo) throws InvalidFileException {
        Path archivoTemporal;
        try {
            archivoTemporal = Paths.get(Parametros.TMP_ARCHIVOS_CARGADOS.concat(archivoDetalleTrabajo.getName()));
            if (Files.exists(archivoTemporal)) {
                Files.delete(archivoTemporal);
            }
            Files.copy(archivoDetalleTrabajo.getInputStream(), archivoTemporal);
        } catch (IOException ioException) {
            LOG.error(Errores.NO_SE_PUDO_OBTENER_PATH_DE_ARCHIVO_INTENTE_NUEVAMENTE, ioException);
            throw new InvalidFileException(Errores.NO_SE_PUDO_OBTENER_PATH_DE_ARCHIVO_INTENTE_NUEVAMENTE);
        }
        return archivoTemporal;
    }

    private List<ElementosDiarios> obtenerElementosPorDia(List<String> lineasArchivo) {
        List<ElementosDiarios> elementosDiarios = new ArrayList<>();
        AtomicInteger posicionLista = new AtomicInteger(0);
        while (lineasArchivo.size() > posicionLista.getAndIncrement() + 1) {
            Integer elementosAMoverDia = Integer.valueOf(lineasArchivo.get(posicionLista.get()));
            List<Elemento> elementos = new ArrayList<>();
            for (int indice = posicionLista.incrementAndGet(); indice < elementosAMoverDia + posicionLista.get(); indice++) {
                elementos.add(new Elemento(Double.valueOf(lineasArchivo.get(indice))));
            }
            posicionLista.getAndAdd(elementosAMoverDia - 1);
            elementosDiarios.add(new ElementosDiarios(elementosAMoverDia, elementos));
        }
        return elementosDiarios;
    }

}
