package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.model.Elemento;
import co.com.mudanzas.management.domain.model.ElementosDiarios;
import co.com.mudanzas.management.exceptions.ItemsSizeDailyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CantidadElementosDiaTest {

    @InjectMocks
    private CantidadElementosDia cantidadElementosDia;

    private DetalleDatosCargados detalleDatosCargados;
    private List<Elemento> elementos;

    @BeforeEach
    public void inicializarDatos() {
        elementos = new ArrayList<>();

        List<ElementosDiarios> elementosDiarios = new ArrayList<>();
        elementosDiarios.add(new ElementosDiarios(1, elementos));
        elementosDiarios.add(new ElementosDiarios(4, elementos));
        elementosDiarios.add(new ElementosDiarios(3, elementos));
        elementosDiarios.add(new ElementosDiarios(3, elementos));
        elementosDiarios.add(new ElementosDiarios(100, elementos));

        detalleDatosCargados = new DetalleDatosCargados();
        detalleDatosCargados.setElementosDiarios(elementosDiarios);
    }

    @Test
    public void debeValidarQueLaCantidadDeElementosAMoverPorDiaSeaMinimoUnoYMaximoCienYRetornarExcepcion() {
        detalleDatosCargados.getElementosDiarios().add(new ElementosDiarios(0, elementos));
        assertThrows(ItemsSizeDailyException.class, () -> cantidadElementosDia.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueLaCantidadDeElementosAMoverPorDiaSeaMinimoUnoYMaximoCienYRetornarExcepcionCasoDos() {
        detalleDatosCargados.getElementosDiarios().add(new ElementosDiarios(101, elementos));
        assertThrows(ItemsSizeDailyException.class, () -> cantidadElementosDia.validar(detalleDatosCargados));
    }

    @Test
    public void debeValidarQueLaCantidadDeElementosAMoverPorDiaSeaMinimoUnoYMaximoCien() {
        assertDoesNotThrow(() -> cantidadElementosDia.validar(detalleDatosCargados));
    }

}
