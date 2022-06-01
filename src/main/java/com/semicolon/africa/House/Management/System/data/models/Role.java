package com.semicolon.africa.House.Management.System.data.models;

import org.springframework.data.annotation.Id;

public class Role {
    @Id
    private String id;

    private RoleType roleType;
}
