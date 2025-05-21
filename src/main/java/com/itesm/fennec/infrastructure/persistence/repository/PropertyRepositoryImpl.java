package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.PaginatedResult;
import com.itesm.fennec.domain.repository.PropertyRepository;
import com.itesm.fennec.infrastructure.persistence.entity.PropertyEntity;
import com.itesm.fennec.infrastructure.persistence.entity.ImagenPropiedadEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.PropertyMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PropertyRepositoryImpl implements PropertyRepository {

    private final EntityManager entityManager;
    private final PropertyMapper mapper;

    @Inject
    public PropertyRepositoryImpl(EntityManager entityManager, PropertyMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public PaginatedResult<Property> getAllProperties(int page, int limit) {
        // Consulta para obtener las propiedades con paginación y relaciones
        TypedQuery<PropertyEntity> query = entityManager.createQuery(
                "SELECT p FROM PropertyEntity p " +
                        "LEFT JOIN FETCH p.tipoPropiedad " +
                        "LEFT JOIN FETCH p.colonia c " +
                        "LEFT JOIN FETCH c.municipio m " +
                        "LEFT JOIN FETCH m.estado",
                PropertyEntity.class
        );

        query.setFirstResult((page - 1) * limit);
        query.setMaxResults(limit);
        List<PropertyEntity> properties = query.getResultList();

        // Consulta para obtener el total de propiedades
        Long total = entityManager.createQuery(
                "SELECT COUNT(p) FROM PropertyEntity p", Long.class
        ).getSingleResult();

        // Obtener las imágenes para cada propiedad
        List<Property> domainProperties = new ArrayList<>();
        for (PropertyEntity entity : properties) {
            List<ImagenPropiedadEntity> images = entityManager.createQuery(
                            "SELECT i FROM ImagenPropiedadEntity i WHERE i.idPropiedad = :idPropiedad",
                            ImagenPropiedadEntity.class
                    )
                    .setParameter("idPropiedad", entity.getIdPropiedad())
                    .getResultList();

            domainProperties.add(mapper.toDomain(entity, images));
        }

        return new PaginatedResult<>(domainProperties, total, page, limit);
    }
}
