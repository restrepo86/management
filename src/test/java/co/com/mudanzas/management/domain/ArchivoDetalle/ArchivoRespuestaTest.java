package co.com.mudanzas.management.domain.ArchivoDetalle;

import co.com.mudanzas.management.exceptions.CreateFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ArchivoRespuestaTest {

    @InjectMocks
    private ArchivoRespuesta archivoRespuesta;

    private List<String> salidaPaquetesPorDia;

    @BeforeEach
    public void inicializarDatos() {
        salidaPaquetesPorDia = Arrays.asList("Case #1: 2", "Case #2: 1", "Case #3: 2");
    }

    @Test
    public void debeConstruirArchivoRespuestaConLosDatosDeSalida() throws CreateFileException {
        byte[] archivoSalida = archivoRespuesta.contruir(salidaPaquetesPorDia);
        assertTrue(archivoSalida.length > 0);
    }

}
