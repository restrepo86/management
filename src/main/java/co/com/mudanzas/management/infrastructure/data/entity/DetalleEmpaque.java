package co.com.mudanzas.management.infrastructure.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "detalle_empaque")
public class DetalleEmpaque implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "cedula_participante")
    private String cedulaParticipante;

    @Column(name = "detalle_datos_cargados", columnDefinition = "text")
    private String detalleDatosCargados;

    @Column(name = "detalle_datos_salida", columnDefinition = "text")
    private String detalleDatosSalida;

    @Column(name = "fecha_ejecucion")
    private Date fechaEjecucion;


    public long getId() {
        return id;
    }

    public String getCedulaParticipante() {
        return cedulaParticipante;
    }

    public void setCedulaParticipante(String cedulaParticipante) {
        this.cedulaParticipante = cedulaParticipante;
    }

    public String getDetalleDatosCargados() {
        return detalleDatosCargados;
    }

    public void setDetalleDatosCargados(String detalleDatosCargados) {
        this.detalleDatosCargados = detalleDatosCargados;
    }

    public String getDetalleDatosSalida() {
        return detalleDatosSalida;
    }

    public void setDetalleDatosSalida(String detalleDatosSalida) {
        this.detalleDatosSalida = detalleDatosSalida;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }
}
