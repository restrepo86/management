package co.com.mudanzas.management.domain.validations;

import co.com.mudanzas.management.domain.contants.Errores;
import co.com.mudanzas.management.domain.model.DetalleDatosCargados;
import co.com.mudanzas.management.exceptions.ItemsSizeDailyException;
import co.com.mudanzas.management.exceptions.ValidationsFileException;
import org.springframework.stereotype.Service;

@Service
public class CantidadElementosDia implements IValidacionesArchivo {

    @Override
    public void validar(DetalleDatosCargados detalleDatosCargados) throws ValidationsFileException {
        boolean tieneElementosPorFueraDelRango = detalleDatosCargados.getElementosDiarios()
                .stream()
                .filter(elementos -> elementos.getCantidad() < 1 || elementos.getCantidad() > 100)
                .findAny()
                .isPresent();
        if (tieneElementosPorFueraDelRango) {
            throw new ItemsSizeDailyException(Errores.EL_NUMERO_DE_ELEMENTOS_PARA_MOVER_DIARIO_DEBE_SER_MINIMO_UNO_Y_MAXIMO_CIEN);
        } else if (detalleDatosCargados.getDiasATrabajar() != detalleDatosCargados.getElementosDiarios().size()) {
            throw new ItemsSizeDailyException(
                    Errores.TODOS_LOS_DIAS_DEBEN_TENER_SU_RESPECTIVA_ASIGNACION_DE_ELEMENTOS_DIARIOS_A_MOVER_POR_EL_TRABAJADOR);
        }
    }

}
