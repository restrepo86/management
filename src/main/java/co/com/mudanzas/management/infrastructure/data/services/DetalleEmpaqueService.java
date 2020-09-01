package co.com.mudanzas.management.infrastructure.data.services;

import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.infrastructure.data.entity.DetalleEmpaque;
import co.com.mudanzas.management.infrastructure.data.repository.IDetalleEmpaqueRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetalleEmpaqueService {

    @Autowired
    private IDetalleEmpaqueRepository iDetalleEmpaqueRepository;

    public void guardar(DetalleDatosCargados detalleDatosCargados, List<String> salidaPaquetesPorDia, String cedulaParticipante) {
        Gson gson = new Gson();
        DetalleEmpaque detalleEmpaque = new DetalleEmpaque();
        detalleEmpaque.setDetalleDatosCargados(gson.toJson(detalleDatosCargados));
        detalleEmpaque.setCedulaParticipante(cedulaParticipante);
        detalleEmpaque.setDetalleDatosSalida(gson.toJson(salidaPaquetesPorDia));
        detalleEmpaque.setFechaEjecucion(new Date());
        iDetalleEmpaqueRepository.save(detalleEmpaque);
    }

}
