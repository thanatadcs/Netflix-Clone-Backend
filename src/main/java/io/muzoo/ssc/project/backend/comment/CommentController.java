package io.muzoo.ssc.project.backend.comment;

import io.muzoo.ssc.project.backend.repo.Comment;
import io.muzoo.ssc.project.backend.repo.CommentRepository;
import io.muzoo.ssc.project.backend.repo.Video;
import io.muzoo.ssc.project.backend.repo.VideoRepository;
import io.muzoo.ssc.project.backend.video.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/api/videos")
    public List<CommentDTO> create(HttpServletRequest request, @RequestParam long video_id) {
        List<Comment> comments =  commentRepository.findAllByVideoId(video_id);
        List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
        for (Comment comment: comments) {
            commentsDTO.add(CommentDTO.builder()
                    .id(comment.getId())
                    .username(comment.getUser().getUsername())
                    .time_stamp(comment.getTime_stamp())
                    .comment(comment.getComment())
                    .build()
            );
        }
        return commentsDTO;
    }


}
