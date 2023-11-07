package com.cheeseocean.jpa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_authority_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityUser {

    @Id
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Role role;

}
