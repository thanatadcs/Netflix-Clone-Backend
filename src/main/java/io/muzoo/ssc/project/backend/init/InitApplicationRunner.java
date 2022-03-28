package io.muzoo.ssc.project.backend.init;

import io.muzoo.ssc.project.backend.repo.User;
import io.muzoo.ssc.project.backend.repo.UserRepository;
import io.muzoo.ssc.project.backend.repo.Video;
import io.muzoo.ssc.project.backend.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // add default admin user and set its password if missing
        User admin = userRepository.findFirstByUsername("admin");
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole("USER");
            userRepository.save(admin);
        }
        User sai = userRepository.findFirstByUsername("sai");
        if (sai == null) {
            sai = new User();
            sai.setUsername("sai");
            sai.setPassword(passwordEncoder.encode("123456"));
            sai.setRole("USER");
            userRepository.save(sai);
        }

        // Add videos
        Video sample1 = videoRepository.findFirstByFilename("sample1");
        if (sample1 == null) {
            sample1 = new Video();
            sample1.setFilename("sample1");
            sample1.setTitle("AV (Animal Video)");
            sample1.setLink("http://157.245.155.41:8082/hls/sample1.mp4/index.m3u8"); // This link will work without installing nginx-vod
            sample1.setThumbnail("https://i.imgur.com/XJRowdx.png");
            videoRepository.save(sample1);
        }
        Video sample2 = videoRepository.findFirstByFilename("sample2");
        if (sample2 == null) {
            sample2 = new Video();
            sample2.setFilename("sample2");
            sample2.setTitle("Ocean");
            sample2.setLink("http://157.245.155.41:8082/hls/sample2.mp4/index.m3u8"); // This link will work without installing nginx-vod
            sample2.setThumbnail("https://i.imgur.com/ucKWSha.jpeg");
            videoRepository.save(sample2);
        }
    }
}
