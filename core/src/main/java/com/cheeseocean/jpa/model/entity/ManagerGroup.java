package com.cheeseocean.jpa.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_manager_group")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerGroup {

    @Id
    private Long id;

    @OneToOne
    private User owner;

    @OneToMany
    private Set<AuthorityUser> members = new HashSet<>();

}
