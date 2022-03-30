package io.muzoo.ssc.project.backend.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.muzoo.ssc.project.backend.repo.User;
import io.muzoo.ssc.project.backend.repo.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommentDTO {
    private long id;

    private String username;

    private float time_stamp;

    private String comment;
}
