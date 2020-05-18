package com.projetoopotimum.optimum.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "nome")
    @NotEmpty(message = "Campo nome obrigatório")
    private String nome;

    @Column(name = "senha")
    @NotEmpty(message = "Campo senha obrigatório")
    private String senha;

}
