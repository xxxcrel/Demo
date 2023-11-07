package com.cheeseocean.jpa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_permission")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    private Long id;

}
