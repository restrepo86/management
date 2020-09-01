package co.com.mudanzas.management.domain.Paquetes;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.model.Elemento;
import co.com.mudanzas.management.domain.model.ElementosDiarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PaquetesTest {

    private static final String CASE_1_2 = "Case #1: 2";
    private static final String CASE_1_1 = "Case #1: 1";
    private static final String CASE_1_3 = "Case #1: 3";
    private static final String CASE_1_8 = "Case #1: 8";

    @InjectMocks
    public Paquetes paquetes;

    private DetalleDatosCargados detalleDatosCargados;
    private DetalleDatosCargados detalleDatosCargadosDos;
    private DetalleDatosCargados detalleDatosCargadosTres;
    private DetalleDatosCargados detalleDatosCargadosCuatro;
    private DetalleDatosCargados detalleDatosCargadosCinco;

    @BeforeEach
    public void inicializarDatos() {

        List<Elemento> pesosItems = new ArrayList<>();
        pesosItems.add(new Elemento(30d));
        pesosItems.add(new Elemento(30d));
        pesosItems.add(new Elemento(1d));
        pesosItems.add(new Elemento(1d));
        detalleDatosCargados = new DetalleDatosCargados();
        List<ElementosDiarios> elementosDiarios = new ArrayList<>();
        elementosDiarios.add(new ElementosDiarios(4, pesosItems));
        detalleDatosCargados.setElementosDiarios(elementosDiarios);

        List<Elemento> pesosItemsDos = new ArrayList<>();
        pesosItemsDos.add(new Elemento(20d));
        pesosItemsDos.add(new Elemento(20d));
        pesosItemsDos.add(new Elemento(20d));
        detalleDatosCargadosDos = new DetalleDatosCargados();
        detalleDatosCargadosDos.setElementosDiarios(Arrays.asList(new ElementosDiarios(3, pesosItemsDos)));

        List<Elemento> pesosItemsTres = new ArrayList<>();
        pesosItemsTres.add(new Elemento(1d));
        pesosItemsTres.add(new Elemento(2d));
        pesosItemsTres.add(new Elemento(3d));
        pesosItemsTres.add(new Elemento(4d));
        pesosItemsTres.add(new Elemento(5d));
        pesosItemsTres.add(new Elemento(6d));
        pesosItemsTres.add(new Elemento(7d));
        pesosItemsTres.add(new Elemento(8d));
        pesosItemsTres.add(new Elemento(9d));
        pesosItemsTres.add(new Elemento(10d));
        pesosItemsTres.add(new Elemento(11d));
        detalleDatosCargadosTres = new DetalleDatosCargados();
        detalleDatosCargadosTres.setElementosDiarios(Arrays.asList(new ElementosDiarios(11, pesosItemsTres)));


        List<Elemento> pesosItemsCuatro = new ArrayList<>();
        pesosItemsCuatro.add(new Elemento(9d));
        pesosItemsCuatro.add(new Elemento(19d));
        pesosItemsCuatro.add(new Elemento(29d));
        pesosItemsCuatro.add(new Elemento(39d));
        pesosItemsCuatro.add(new Elemento(49d));
        pesosItemsCuatro.add(new Elemento(59d));
        detalleDatosCargadosCuatro = new DetalleDatosCargados();
        detalleDatosCargadosCuatro.setElementosDiarios(Arrays.asList(new ElementosDiarios(6, pesosItemsCuatro)));


        List<Elemento> pesosItemsCinco = new ArrayList<>();
        pesosItemsCinco.add(new Elemento(32d));
        pesosItemsCinco.add(new Elemento(56d));
        pesosItemsCinco.add(new Elemento(76d));
        pesosItemsCinco.add(new Elemento(8d));
        pesosItemsCinco.add(new Elemento(44d));
        pesosItemsCinco.add(new Elemento(60d));
        pesosItemsCinco.add(new Elemento(47d));
        pesosItemsCinco.add(new Elemento(85d));
        pesosItemsCinco.add(new Elemento(71d));
        pesosItemsCinco.add(new Elemento(91d));
        detalleDatosCargadosCinco = new DetalleDatosCargados();
        detalleDatosCargadosCinco.setElementosDiarios(Arrays.asList(new ElementosDiarios(10, pesosItemsCinco)));

    }

    @Test
    public void debeOrganizarLosItemsEnBolsas() {
        List<String> bolsas = paquetes.almacenar(detalleDatosCargados);
        assertNotNull(bolsas);
    }

    @Test
    public void debeEmpacarBolsasEnLasQueLaMultiplicacionDelPesoDelItemDeArribaPorLaCantidadDeLaBolsaDe50MinimoParaUnNumeroMayorACincuenta() {
        detalleDatosCargados.getElementosDiarios().add(new ElementosDiarios(1, Arrays.asList(new Elemento(50d))));
        List<String> bolsas = paquetes.almacenar(detalleDatosCargados);
        assertEquals(CASE_1_2, bolsas.get(0));
    }

    @Test
    public void debeEmpacarBolsasEnLasQueLaMultiplicacionDelPesoDelItemDeArribaPorLaCantidadDeLaBolsaDe50MinimoDebeRetornarDosBolsasParaPesosItems() {
        List<String> bolsas = paquetes.almacenar(detalleDatosCargados);
        assertEquals(CASE_1_2, bolsas.get(0));
    }

    @Test
    public void debeEmpacarBolsasEnLasQueLaMultiplicacionDelPesoDelItemDeArribaPorLaCantidadDeLaBolsaDe50MinimoDebeRetornarUnaBolsa() {
        List<String> bolsas = paquetes.almacenar(detalleDatosCargadosDos);
        assertEquals(CASE_1_1, bolsas.get(0));
    }

    @Test
    public void debeEmpacarBolsasEnLasQueLaMultiplicacionDelPesoDelItemDeArribaPorLaCantidadDeLaBolsaDe50MinimoDebeRetornarDosBolsas() {
        List<String> bolsas = paquetes.almacenar(detalleDatosCargadosTres);
        assertEquals(CASE_1_2, bolsas.get(0));
    }

    @Test
    public void debeEmpacarBolsasEnLasQueLaMultiplicacionDelPesoDelItemDeArribaPorLaCantidadDeLaBolsaDe50MinimoDebeRetornarTresBolsas() {
        List<String> bolsas = paquetes.almacenar(detalleDatosCargadosCuatro);
        assertEquals(CASE_1_3, bolsas.get(0));
    }

    @Test
    public void debeEmpacarBolsasEnLasQueLaMultiplicacionDelPesoDelItemDeArribaPorLaCantidadDeLaBolsaDe50MinimoDebeRetornarOchoBolsas() {
        List<String> bolsas = paquetes.almacenar(detalleDatosCargadosCinco);
        assertEquals(CASE_1_8, bolsas.get(0));
    }


}
