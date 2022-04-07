package io.muzoo.ssc.project.backend.mylist;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.comment.CommentDTO;
import io.muzoo.ssc.project.backend.repo.*;
import io.muzoo.ssc.project.backend.video.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MylistController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/api/videos/mylist")
    public MylistDTO create(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        User user = userRepository.findFirstByUsername(username);
        List<VideoDTO> videoList = new ArrayList<>();
        for(String vidId : user.getMylistvideo()){
            Video video = videoRepository.findFirstById(Long.parseLong(vidId));
            videoList.add(VideoDTO.builder().id(video.getId())
                    .filename(video.getFilename())
                    .link(video.getLink())
                    .thumbnail(video.getThumbnail())
                    .tags(video.getTags())
                    .title(video.getTitle()).build());
        }
        return MylistDTO.builder().mylistvideo(videoList).build();
    }

    @Transactional
    @PostMapping("/api/videos/mylist/add")
    public SimpleResponseDTO update(HttpServletRequest request) {
        long videoId = Long.parseLong(request.getParameter("videoId"));

        // Get user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        User user = userRepository.findFirstByUsername(username);

        // Find existing video list
        List<String> videoIdList = user.getMylistvideo();
        if(videoIdList == null || videoIdList.isEmpty()){
            videoIdList = new ArrayList<>();
        }

        // Set mylist and return response
        if(!videoIdList.contains(String.valueOf(videoId))){
            videoIdList.add(String.valueOf(videoId));
        }
        else{
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message(String.format("Cannot add video id %s for user %s. It already exist in the playlist",
                            videoId, user.getUsername()))
                    .build();
        }
        user.setMylistvideo(videoIdList);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message(String.format("Added video id %s into video list successfully for user %s",
                        videoId, user.getUsername()))
                .build();
    }

    @Transactional
    @PostMapping("/api/videos/mylist/remove")
    public SimpleResponseDTO remove(HttpServletRequest request) {
        long videoId = Long.parseLong(request.getParameter("videoId"));

        // Get user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        User user = userRepository.findFirstByUsername(username);

        List<String> videoIdList = user.getMylistvideo();
        if (videoIdList == null || videoIdList.isEmpty()) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message(String.format("Cannot remove video id %s for user %s. User has empty list",
                            videoId, user.getUsername()))
                    .build();
        }
        else if(!videoIdList.contains(String.valueOf(videoId))){
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message(String.format("Cannot remove video id %s for user %s. User don't have the video in the playlist",
                            videoId, user.getUsername()))
                    .build();
        }

        // Set mylist and return response
        videoIdList.remove(String.valueOf(videoId));
        user.setMylistvideo(videoIdList);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message(String.format("Removed video id %s into video list successfully for user %s",
                        videoId, user.getUsername()))
                .build();
    }

}
