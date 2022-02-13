package com.cheeseocean.jpa.model.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Comment")
@Table(name = "tbl_comment")
@DynamicUpdate
@DynamicInsert
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Comment parent;

    private String content;

    private LocalDateTime createdAt;

    private int starCount = 0;

    private int subCommentCount = 0;
}
