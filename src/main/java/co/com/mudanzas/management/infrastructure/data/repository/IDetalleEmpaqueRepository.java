package co.com.mudanzas.management.infrastructure.data.repository;

import co.com.mudanzas.management.infrastructure.data.entity.DetalleEmpaque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleEmpaqueRepository extends CrudRepository<DetalleEmpaque, Long> {
}
