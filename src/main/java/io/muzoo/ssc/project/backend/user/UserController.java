package io.muzoo.ssc.project.backend.user;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.repo.User;
import io.muzoo.ssc.project.backend.repo.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping("/api/create")
    public SimpleResponseDTO create(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // All new user if username does not already exists
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("USER");
//            user.setTimestamp(0);
            userRepository.save(user);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message(String.format("Created user %s successfully.", username))
                    .build();
        } else {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message(String.format("Username %s already exists", username))
                    .build();
        }
    }

    @Transactional
    @GetMapping("/api/delete")
    public SimpleResponseDTO delete(HttpServletRequest request) {
        try {
            // Check if there is a current user logged in, if so log that user out first
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof org.springframework.security.core.userdetails.User) {
                request.logout();
                String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
                userRepository.deleteByUsername(username);
                return SimpleResponseDTO
                        .builder()
                        .success(true)
                        .message(String.format("User %s is successfully deleted.", username))
                        .build();
            } else {
                return SimpleResponseDTO
                        .builder()
                        .success(false)
                        .message("Can't delete user, you are not logged in.")
                        .build();
            }
        } catch (ServletException e) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Failed to delete user.")
                    .build();
        }
    }
}
