package io.muzoo.ssc.project.backend.video;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.repo.Video;
import io.muzoo.ssc.project.backend.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/api/videos")
    public List<Video> create(HttpServletRequest request) {
        List<Video> videos =  videoRepository.findAll();
        return videos;
    }
}
