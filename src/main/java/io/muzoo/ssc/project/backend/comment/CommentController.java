package io.muzoo.ssc.project.backend.comment;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.repo.*;
import io.muzoo.ssc.project.backend.video.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimestampRepository timestampRepository;


    @GetMapping("/api/videos/comment")
    public List<CommentDTO> create(HttpServletRequest request) {
        long video_id = Long.parseLong(request.getParameter("videoId"));
        List<Comment> comments =  commentRepository.findAllByVideo_Id(video_id);
        List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
        for (Comment comment: comments) {
            commentsDTO.add(CommentDTO.builder()
                    .id(comment.getId())
                    .username(comment.getUser().getUsername())
                    .timestamp(comment.getTimestamp())
                    .comment(comment.getComment())
                    .build()
            );
        }
        return commentsDTO;
    }

    @Transactional
    @PostMapping("/api/videos/comment/update")
    public SimpleResponseDTO update(HttpServletRequest request) {
        long videoId = Long.parseLong(request.getParameter("videoId"));
        String insertedComment = request.getParameter("comment");

        // Find video
        Video video = videoRepository.findFirstById(videoId);

        // Get user information
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        User user = userRepository.findFirstByUsername(username);

        // Find existing timestamp
        Timestamp timestamp = timestampRepository.findFirstByUser_IdAndVideo_Id(video.getId(), user.getId());

        // Create new comment
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setVideo(video);
        comment.setTimestamp(timestamp.getTimestamp());
        comment.setComment(insertedComment);
        commentRepository.save(comment);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message(String.format("Updated comment to be %s successfully for user %s and video %s at timestamp %f",
                        insertedComment, user.getUsername(), video.getFilename(), timestamp.getTimestamp()))
                .build();
    }

}
