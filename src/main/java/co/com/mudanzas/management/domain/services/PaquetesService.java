package co.com.mudanzas.management.domain.services;

import co.com.mudanzas.management.domain.ArchivoDetalle.ArchivoDetalleTrabajo;
import co.com.mudanzas.management.domain.Paquetes.Paquetes;
import co.com.mudanzas.management.domain.validations.ValidationesArchivo;
import co.com.mudanzas.management.infraestructura.data.services.DetalleEmpaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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

    public List<String> almacenarEnBolsas(File archivoDetalle) {
        List<Double> valoresArchivos = archivoDetalleTrabajo.cargar(archivoDetalle);
        validacionesArchivo.ejecutar(valoresArchivos);
        List<String> bolsasPorDia = paquetes.almacenar(valoresArchivos);
        detalleEmpaqueService.guardar(bolsasPorDia);
        return bolsasPorDia;
    }
}
