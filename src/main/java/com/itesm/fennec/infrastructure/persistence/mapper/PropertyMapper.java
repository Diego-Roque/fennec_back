package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.infrastructure.dto.PropertyResponseDTO;
import com.itesm.fennec.infrastructure.persistence.entity.ImagenPropiedadEntity;
import com.itesm.fennec.infrastructure.persistence.entity.PropertyEntity;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PropertyMapper {

    public Property toDomain(PropertyEntity entity, List<ImagenPropiedadEntity> images) {
        Property model = new Property();
        model.setId(entity.getIdPropiedad());
        model.setTitle(entity.getTitulo());
        model.setDescription(entity.getDescripcion());
        model.setPrice(entity.getPrecio());
        model.setArea(entity.getSuperficie());
        model.setBedrooms(entity.getHabitaciones());
        model.setBathrooms(entity.getBanos());
        model.setParkingSpaces(entity.getEstacionamiento());

        if (entity.getTipoPropiedad() != null) {
            model.setPropertyTypeId(entity.getTipoPropiedad().getIdTipoPropiedad());
            model.setPropertyTypeName(entity.getTipoPropiedad().getNombre());
        }

        if (entity.getColonia() != null) {
            model.setNeighborhoodId(entity.getColonia().getIdColonia());
            model.setNeighborhoodName(entity.getColonia().getNombre());

            if (entity.getColonia().getMunicipio() != null) {
                model.setMunicipalityId(entity.getColonia().getMunicipio().getIdMunicipio());
                model.setMunicipalityName(entity.getColonia().getMunicipio().getNombre());

                if (entity.getColonia().getMunicipio().getEstado() != null) {
                    model.setStateId(entity.getColonia().getMunicipio().getEstado().getIdEstado());
                    model.setStateName(entity.getColonia().getMunicipio().getEstado().getNombre());
                }
            }
        }

        model.setImages(images.stream()
                .map(ImagenPropiedadEntity::getUrlImagen)
                .collect(Collectors.toList()));

        return model;
    }

    public PropertyResponseDTO toDTO(Property model) {
        PropertyResponseDTO dto = new PropertyResponseDTO();
        dto.setIdPropiedad(model.getId());
        dto.setTitulo(model.getTitle());
        dto.setDescripcion(model.getDescription());
        dto.setPrecio(model.getPrice());
        dto.setSuperficie(model.getArea());
        dto.setHabitaciones(model.getBedrooms());
        dto.setBanos(model.getBathrooms());
        dto.setEstacionamiento(model.getParkingSpaces());
        dto.setTipoPropiedad(model.getPropertyTypeName());
        dto.setColonia(model.getNeighborhoodName());
        dto.setMunicipio(model.getMunicipalityName());
        dto.setEstado(model.getStateName());
        dto.setImagenes(model.getImages());
        return dto;
    }
}
