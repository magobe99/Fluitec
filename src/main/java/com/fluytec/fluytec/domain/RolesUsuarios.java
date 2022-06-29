
package com.fluytec.fluytec.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles_usuarios")
public class RolesUsuarios {
    @Id
    private Integer roles_id_rol;
    private Integer usuarios_id_usuarios;
}
