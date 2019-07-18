package com.weebindustry.weebjournal.models;


import java.util.Date;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post{
    
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "date_created")
    private Date dateCreated;

    private int votes;

    private int saved;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User users;
    
}