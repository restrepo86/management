package co.com.mudanzas.management.infrastructure.http.rest;

import co.com.mudanzas.management.domain.services.PaquetesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;

@RestController
@RequestMapping("/paquetes")
public class PaquetesController {

    @Autowired
    private PaquetesService paquetesService;

    @PostMapping("/almacenar")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    public void almacenarEnBolsas(File archivoDetalleTrabajo) {
        paquetesService.almacenarEnBolsas(archivoDetalleTrabajo);
    }

}
