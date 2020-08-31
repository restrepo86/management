package co.com.mudanzas.management.domain.services;

import co.com.mudanzas.management.domain.ArchivoDetalle.ArchivoDetalleTrabajo;
import co.com.mudanzas.management.domain.Paquetes.Paquetes;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.validations.ValidationesArchivo;
import co.com.mudanzas.management.exceptions.InvalidFileException;
import co.com.mudanzas.management.infrastructure.data.services.DetalleEmpaqueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PaquetesService {

    private static final Logger LOG = LoggerFactory.getLogger(PaquetesService.class);

    @Autowired
    private ArchivoDetalleTrabajo archivoDetalleTrabajo;

    @Autowired
    private ValidationesArchivo validacionesArchivo;

    @Autowired
    private Paquetes paquetes;

    @Autowired
    private DetalleEmpaqueService detalleEmpaqueService;

    public DetalleDatosCargados almacenarEnBolsas(MultipartFile archivoDetalle) {
        DetalleDatosCargados detalleDatosCargados = null;
        try {
            detalleDatosCargados = archivoDetalleTrabajo.cargar(archivoDetalle);
            validacionesArchivo.ejecutar(detalleDatosCargados);
            List<String> bolsasPorDia = paquetes.almacenar(detalleDatosCargados);
            detalleEmpaqueService.guardar(bolsasPorDia);
        } catch (InvalidFileException e) {
            LOG.error(e.getMessage(), e);
        }
        return detalleDatosCargados;
    }
}
