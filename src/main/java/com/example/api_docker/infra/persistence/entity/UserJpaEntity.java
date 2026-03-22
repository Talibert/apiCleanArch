package com.example.api_docker.infra.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "users")
@Table(name = "users")
@Data
public class UserJpaEntity extends AbstractJpaEntity {

    private String name;
}
