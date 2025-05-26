package com.itesm.fennec.infrastructure.persistence.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "inversion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InversionEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inversion")
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Integer userId;

    @Column(name = "id_propiedad", nullable = false)
    private Long propertyId;

    @Column(name = "monto_invertido", nullable = false)
    private BigDecimal amountInvested;

    @Column(name = "porcentaje_participacion")
    private BigDecimal participationPercentage;

    @Column(name = "fecha_inversion", nullable = false)
    private LocalDate investmentDate;


}
