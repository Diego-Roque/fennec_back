package com.itesm.fennec.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="usuario")
@Getter
@Setter
public class UserEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="firebase_id", nullable = false, unique = true, length = 255)
    private String firebaseId;

    @Column(name="nombre", length = 100)
    private String nombre;

    @Column(name="telefono", length = 20)
    private String telefono;

    @Column(name="tipo_role")
    private String tipoRole;
}
