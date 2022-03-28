package io.muzoo.ssc.project.backend.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoDTO {

    private long id;

    private String filename;

    private String link;

    private String thumbnail;

    private String title;
}
