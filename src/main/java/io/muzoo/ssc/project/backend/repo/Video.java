package io.muzoo.ssc.project.backend.repo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "user")
    Set<Timestamp> timestamps;
}
