package com.weebindustry.weebjournal.models;


import java.util.Date;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    
    private static final long serialVersionUID = 7441073095469088061L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "votes")
    private int votes;

    @Column(name = "saved")
    private int saved;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE) 
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore 
    private User user;

}