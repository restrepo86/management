package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.model.Elemento;
import co.com.mudanzas.management.domain.model.ElementosDiarios;
import co.com.mudanzas.management.exceptions.InvalidWeightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PesoElementosTest {

    @InjectMocks
    private PesoElementos pesoElementos;

    private DetalleDatosCargados detalleDatosCargados;
    private DetalleDatosCargados detalleDatosCargadosPesoMenosDelMinimo;

    @BeforeEach
    public void inicializarDatos() {
        List<ElementosDiarios> elementosDiarios = new ArrayList<>();
        elementosDiarios.add(new ElementosDiarios(4, Arrays.asList(
                new Elemento(1d),
                new Elemento(10d),
                new Elemento(25d),
                new Elemento(100d)
                )));
        detalleDatosCargados = new DetalleDatosCargados();
        detalleDatosCargados.setElementosDiarios(elementosDiarios);

        List<ElementosDiarios> elementosDiariosPesoMinimo = new ArrayList<>();
        elementosDiariosPesoMinimo.add(new ElementosDiarios(4, Arrays.asList(
                new Elemento(1d),
                new Elemento(1d),
                new Elemento(2d),
                new Elemento(1d)
        )));
        detalleDatosCargadosPesoMenosDelMinimo = new DetalleDatosCargados();
        detalleDatosCargadosPesoMenosDelMinimo.setElementosDiarios(elementosDiariosPesoMinimo);

    }

    @Test
    public void debeValidarQueElPesoMinimoDeLosElementosSeaUnoYElPesoMaximoSeaCien() {
        assertDoesNotThrow(() -> pesoElementos.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueElPesoMinimoDeLosElementosSeaUnoYElPesoMaximoSeaCienYRetornarExcepcion() {
        detalleDatosCargados.getElementosDiarios()
                .add(new ElementosDiarios(1, Arrays.asList(new Elemento(0))));
        assertThrows(InvalidWeightException.class, () -> pesoElementos.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueElPesoMinimoDeLosElementosSeaUnoYElPesoMaximoSeaCienYRetornarExcepcionCasoDos() {
        detalleDatosCargados.getElementosDiarios()
                .add(new ElementosDiarios(1, Arrays.asList(new Elemento(101))));
        assertThrows(InvalidWeightException.class, () -> pesoElementos.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueElPesoTotalDeLosElementosDiariosSeaMinimoCincuentaLibrasYLanzarExcepcion() {
        assertThrows(InvalidWeightException.class, () -> pesoElementos.validar(detalleDatosCargadosPesoMenosDelMinimo));
    }

    @Test
    public void debeValidarQueElPesoTotalDeLosElementosDiariosSeaMinimoCincuentaLibras() {
        assertThrows(InvalidWeightException.class, () -> pesoElementos.validar(detalleDatosCargadosPesoMenosDelMinimo));
    }



}
