package co.com.mudanzas.management.infrastructure.data.services;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.infrastructure.data.entity.DetalleEmpaque;
import co.com.mudanzas.management.infrastructure.data.repository.IDetalleEmpaqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DetalleEmpaqueServiceTest {

    private static final String CEDULA_PARTICIPANTE = "1023493238";
    private static final String CASE_1_2 = "Case #1: 2";
    private static final String CASE_2_1 = "Case #2: 1";
    private static final String CASE_3_2 = "Case #3: 2";

    @InjectMocks
    private DetalleEmpaqueService detalleEmpaqueService;

    @Mock
    private IDetalleEmpaqueRepository iDetalleEmpaqueRepository;

    private DetalleDatosCargados detalleDatosCargados;
    private List<String> salidaPaquetesPorDia;

    @BeforeEach
    public void inicializarDatos() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        detalleDatosCargados = podamFactory.manufacturePojo(DetalleDatosCargados.class);
        salidaPaquetesPorDia = Arrays.asList(CASE_1_2, CASE_2_1, CASE_3_2);
    }

    @Test
    public void debeGuardarTrazaDeLasEjecucionesDeCargaEnElSistema() {
        detalleEmpaqueService.guardar(detalleDatosCargados, salidaPaquetesPorDia, CEDULA_PARTICIPANTE);
        verify(iDetalleEmpaqueRepository).save(any(DetalleEmpaque.class));
    }

}
