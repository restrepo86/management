package co.com.mudanzas.management.domain.model;

import org.springframework.web.multipart.MultipartFile;

public class DatosEntrada {

    private String cedulaParticipante;
    private MultipartFile archivoDetalleTrabajo;

    public DatosEntrada() {
        super();
    }

    public DatosEntrada(String cedulaParticipante, MultipartFile archivoDetalleTrabajo) {
        this.cedulaParticipante = cedulaParticipante;
        this.archivoDetalleTrabajo = archivoDetalleTrabajo;
    }

    public String getCedulaParticipante() {
        return cedulaParticipante;
    }

    public void setCedulaParticipante(String cedulaParticipante) {
        this.cedulaParticipante = cedulaParticipante;
    }

    public MultipartFile getArchivoDetalleTrabajo() {
        return archivoDetalleTrabajo;
    }

    public void setArchivoDetalleTrabajo(MultipartFile archivoDetalleTrabajo) {
        this.archivoDetalleTrabajo = archivoDetalleTrabajo;
    }
}
