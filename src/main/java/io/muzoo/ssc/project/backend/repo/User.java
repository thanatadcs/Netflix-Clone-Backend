package io.muzoo.ssc.project.backend.repo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @OneToMany(mappedBy = "video")
    Set<Timestamp> timestamps;

    @OneToMany(mappedBy = "video")
    List<Comment> comments;

    @ElementCollection
    private List<String> mylistvideo;

}
