package co.com.mudanzas.management.infrastructure.http.rest;

import co.com.mudanzas.management.domain.services.PaquetesService;
import co.com.mudanzas.management.exceptions.ManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/paquetes")
public class PaquetesController {

    @Autowired
    private PaquetesService paquetesService;

    @PostMapping(
            value = "/almacenar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<String> almacenarEnBolsas(@RequestParam MultipartFile archivoDetalleTrabajo) {
        try {
            return paquetesService.almacenarEnBolsas(archivoDetalleTrabajo);
        } catch (ManagementException managementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, managementException.getMessage());
        }
    }

}
