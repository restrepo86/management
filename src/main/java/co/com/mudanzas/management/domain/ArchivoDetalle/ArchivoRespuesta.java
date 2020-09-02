package co.com.mudanzas.management.domain.ArchivoDetalle;

import co.com.mudanzas.management.domain.contants.Errores;
import co.com.mudanzas.management.domain.contants.Parametros;
import co.com.mudanzas.management.exceptions.CreateFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ArchivoRespuesta {

    private static final Logger LOG = LoggerFactory.getLogger(ArchivoRespuesta.class);

    public byte[] contruir(List<String> salidaPaquetesPorDia) throws CreateFileException {
        try {
            Path path = Paths.get(Parametros.TMP_ARCHIVOS_CARGADOS.concat(Parametros.ARCHIVO_SALIDA_TXT));
            Files.deleteIfExists(path);
            Path archivoSalida =
                    Files.createFile(path);
            Files.write(archivoSalida, salidaPaquetesPorDia, StandardCharsets.UTF_8);
            return Files.readAllBytes(archivoSalida);
        } catch (IOException e) {
            LOG.error("No se pudo crear archivo de salida por -> ", e);
            throw new CreateFileException(Errores.NO_SE_PUDO_CREAR_ARCHIVO_DE_SALIDA_INTENTE_NUEVAMENTE);
        }
    }

}
