package co.com.mudanzas.management.domain.services;

import co.com.mudanzas.management.domain.ArchivoDetalle.ArchivoDetalleTrabajo;
import co.com.mudanzas.management.domain.ArchivoDetalle.ArchivoRespuesta;
import co.com.mudanzas.management.domain.Paquetes.Paquetes;
import co.com.mudanzas.management.domain.model.DatosEntrada;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.domain.validations.ValidationesArchivo;
import co.com.mudanzas.management.exceptions.ManagementException;
import co.com.mudanzas.management.infrastructure.data.services.DetalleEmpaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PaquetesService {

    @Autowired
    private ArchivoDetalleTrabajo archivoDetalleTrabajo;

    @Autowired
    private ValidationesArchivo validacionesArchivo;

    @Autowired
    private Paquetes paquetes;

    @Autowired
    private DetalleEmpaqueService detalleEmpaqueService;

    @Autowired
    private ArchivoRespuesta archivoRespuesta;

    public byte[] almacenarEnBolsas(DatosEntrada datosEntrada) throws ManagementException {
        DetalleDatosCargados detalleDatosCargados = archivoDetalleTrabajo.cargar(datosEntrada.getArchivoDetalleTrabajo());
        validacionesArchivo.ejecutar(detalleDatosCargados);
        List<String> bolsasPorDia = paquetes.almacenar(detalleDatosCargados);
        detalleEmpaqueService.guardar(detalleDatosCargados, bolsasPorDia, datosEntrada.getCedulaParticipante());
        return archivoRespuesta.contruir(bolsasPorDia);
    }
}
