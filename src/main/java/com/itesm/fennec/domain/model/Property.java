// domain/model/Property.java
package com.itesm.fennec.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class Property {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal area;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer parkingSpaces;
    private Long propertyTypeId;
    private String propertyTypeName;
    private Long neighborhoodId;
    private String neighborhoodName;
    private Long municipalityId;
    private String municipalityName;
    private Long stateId;
    private String stateName;
    private List<String> images;

}
