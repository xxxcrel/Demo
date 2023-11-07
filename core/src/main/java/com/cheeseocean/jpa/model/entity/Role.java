package com.cheeseocean.jpa.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_role")
@Data
public class Role {

    @Id
    private Long id;

    private String name;

    private String desc;

//    @OneToMany
//    private Set<Permission> permissions = new HashSet<>();

}
