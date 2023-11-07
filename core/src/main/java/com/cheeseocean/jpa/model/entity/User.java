package com.cheeseocean.jpa.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "tbl_user")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraphs(
        value = {
                @NamedEntityGraph(
                        name = "User.postsFetchImages",
                        attributeNodes = @NamedAttributeNode(value = "posts", subgraph = "User.postsFetchImages.images"),
                        subgraphs = @NamedSubgraph(name = "User.postsFetchImages.images", attributeNodes = @NamedAttributeNode("images"))
                ),
                @NamedEntityGraph(
                        name = "User.postsFetchAll",
                        attributeNodes = @NamedAttributeNode(value = "posts", subgraph = "User.postsFetchAll.all"),
                        subgraphs = @NamedSubgraph(name = "User.postsFetchAll.all", attributeNodes = {@NamedAttributeNode("images"), @NamedAttributeNode("comments")})
                )
        }
)
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    private static final long serialVersionUID = 749792921653839187L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //eg:studentId, teacherId, staffId...
    @Column( unique = true)
    private String cheeseID;

    @Column(unique = true)
    private String phone;

    private String password;

    private String nickname;

    @Column(unique = true)
    private String email;

    @OneToOne
    private Image avatar;

    //eg:0793-江西上饶、0731-湖南长沙
    private String region = null;

    private String bio;

    private Date birth;

    //0-male, 1-female
    private Integer gender;

    //如果type是student，采用.分隔（eg:0.17.12.2代表本科17级软件工程2班）
    @Column(nullable = true)
    private String studentAttr;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_user_post")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_user_comment")
    private Set<Comment> comments = new HashSet<>();
}
