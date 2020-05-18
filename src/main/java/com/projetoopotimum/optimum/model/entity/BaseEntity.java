package com.projetoopotimum.optimum.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime updatedAt;
}
