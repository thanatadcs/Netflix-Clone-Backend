package io.muzoo.ssc.project.backend.mylist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.muzoo.ssc.project.backend.repo.User;
import io.muzoo.ssc.project.backend.repo.Video;
import io.muzoo.ssc.project.backend.video.VideoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MylistDTO {
    private List<VideoDTO> mylistvideo;
}
