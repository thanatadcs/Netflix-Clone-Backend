package io.muzoo.ssc.project.backend.timestamp;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
public class TimestampController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TimestampRepository timestampRepository;

    @Transactional
    @PostMapping("/api/timestamp/update")
    public SimpleResponseDTO update(HttpServletRequest request) {
        long videoId = Long.parseLong(request.getParameter("videoId"));
        float updatedTimestamp = Float.parseFloat(request.getParameter("timestamp"));

        // Get user information
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        User user = userRepository.findFirstByUsername(username);

        Timestamp timestamp = timestampRepository.findFirstByUser_IdAndVideo_Id(user.getId(), videoId);
        Video video = videoRepository.findFirstById(videoId);

        // Check if timestamp already exists
        if (timestamp == null && user != null && video != null) {
                timestamp = new Timestamp();
                timestamp.setUser(user);
                timestamp.setVideo(video);
                timestamp.setTimestamp(updatedTimestamp);
                timestampRepository.save(timestamp);
                return SimpleResponseDTO
                        .builder()
                        .success(true)
                        .message(String.format("Updated timestamp to be %f successfully for user %s and video %s",
                                updatedTimestamp, user.getUsername(), video.getFilename()))
                        .build();
        } else {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Failed to update timestamp")
                    .build();
        }
    }

    @PostMapping("/api/timestamp/get")
    public TimestampDTO get(HttpServletRequest request) {
        long videoId = Long.parseLong(request.getParameter("videoId"));

        // Get user information
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        User user = userRepository.findFirstByUsername(username);

        Timestamp timestamp = timestampRepository.findFirstByUser_IdAndVideo_Id(user.getId(), videoId);
        Video video = videoRepository.findFirstById(videoId);
        // Create time stamp if not exists
        if (timestamp == null) {
            timestamp = new Timestamp();
            timestamp.setUser(user);
            timestamp.setVideo(video);
            timestamp.setTimestamp(0);
            timestampRepository.save(timestamp);
            return TimestampDTO.builder()
                    .success(true)
                    .timestamp(0)
                    .build();
        // If timestamp exists
        } else if (user != null && video != null){
            return TimestampDTO.builder()
                    .success(true)
                    .timestamp(timestamp.getTimestamp())
                    .build();
        } else {
            return TimestampDTO.builder()
                    .success(false)
                    .timestamp(0)
                    .build();
        }
    }
}
