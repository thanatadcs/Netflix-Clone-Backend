package io.muzoo.ssc.project.backend.video;

import io.muzoo.ssc.project.backend.repo.Video;
import io.muzoo.ssc.project.backend.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/api/videos")
    public List<VideoDTO> create(HttpServletRequest request) {
        List<Video> videos =  videoRepository.findAll();
        List<VideoDTO> videosDTO = new ArrayList<VideoDTO>();
        // We are using videoDTO instead because, video got Set<Timestamp> which got mapped to user,
        // this creates error when trying to send the entity Video
        for (Video video: videos) {
            videosDTO.add(VideoDTO.builder()
                    .id(video.getId())
                    .filename(video.getFilename())
                    .link(video.getLink())
                    .thumbnail(video.getThumbnail())
                    .title(video.getTitle())
                    .description(video.getDescription())
                    .build()
            );
        }
        return videosDTO;
    }
}
