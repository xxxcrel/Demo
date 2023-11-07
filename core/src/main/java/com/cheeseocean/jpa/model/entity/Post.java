package com.cheeseocean.jpa.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Post")
@Table(name = "tbl_post")
@DynamicUpdate
@DynamicInsert
//@Data
//@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"images", "comments"})
@NoArgsConstructor
@NamedEntityGraph(
        name = "Post.details",
        attributeNodes = {
                @NamedAttributeNode("images"),
        }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    /**
     * 标签，使用逗号分开
     */
    private String tags;

    @ManyToOne
    private Category category;

    private LocalDateTime createdAt;

    private Integer starCount = 0;

    private Integer commentCount = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_post_image")
    private Set<Image> images = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_post_comment")
    private Set<Comment> comments = new HashSet<>();
}