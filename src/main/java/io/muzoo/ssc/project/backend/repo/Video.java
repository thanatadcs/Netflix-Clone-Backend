package io.muzoo.ssc.project.backend.repo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tbl_video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String filename;

    @Column(unique = true)
    private String link;

    private String thumbnail;

    private String title;

    private String description;

    @ElementCollection
    private List<String> tags;
  
    @OneToMany(mappedBy = "user")
    Set<Timestamp> timestamps;

    @OneToMany(mappedBy = "user")
    List<Comment> comments;
}
