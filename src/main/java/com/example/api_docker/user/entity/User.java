package com.example.api_docker.user.entity;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
}
