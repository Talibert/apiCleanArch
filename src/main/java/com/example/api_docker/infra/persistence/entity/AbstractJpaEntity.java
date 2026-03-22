package com.example.api_docker.infra.persistence.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractJpaEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;
}
