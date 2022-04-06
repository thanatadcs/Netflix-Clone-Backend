package io.muzoo.ssc.project.backend.init;

import io.muzoo.ssc.project.backend.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommentRepository commentRepository;

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
            sample1.setDescription("Short b-roll footage of animals in thier natural habitat");
            videoRepository.save(sample1);
        }
        else if (sample1.getDescription() == null) {
            sample1.setDescription("Short b-roll footage of animals in thier natural habitat");
            videoRepository.save(sample1);
        }
        Video sample2 = videoRepository.findFirstByFilename("sample2");
        if (sample2 == null) {
            sample2 = new Video();
            sample2.setFilename("sample2");
            sample2.setTitle("Ocean");
            sample2.setLink("http://157.245.155.41:8082/hls/sample2.mp4/index.m3u8"); // This link will work without installing nginx-vod
            sample2.setThumbnail("https://i.imgur.com/ucKWSha.jpeg");
            sample2.setDescription("Short b-roll footage of some ocean animals");
            videoRepository.save(sample2);
        }
        else if (sample2.getDescription() == null) {
            sample2.setDescription("Short b-roll footage of some ocean animals");
            videoRepository.save(sample2);
        }
        Video sao_ep1 = videoRepository.findFirstByFilename("SAO_ep1");
        if (sao_ep1 == null) {
            sao_ep1 = new Video();
            sao_ep1.setFilename("SAO_ep1");
            sao_ep1.setTitle("SAO_ep1");
            sao_ep1.setLink("http://157.245.155.41:8082/hls/SAO-ep1.mp4/index.m3u8"); // This link will work without installing nginx-vod
            sao_ep1.setThumbnail("https://i.imgur.com/yhXzB7F.png");
            sao_ep1.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep1);
        }
        else if (sao_ep1.getDescription() == null) {
            sao_ep1.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep1);
        }
        Comment sample1_comment = commentRepository.findFirstByVideo_IdAndUser_IdAndTimestamp(1, 1, 0);
        if(sample1_comment == null){
            sample1_comment = new Comment();
            sample1_comment.setVideo(videoRepository.findFirstByFilename("sample1"));
            sample1_comment.setUser(userRepository.findFirstByUsername("admin"));
            sample1_comment.setTimestamp(0);
            sample1_comment.setComment("First comment");
            commentRepository.save(sample1_comment);
        }
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole("USER");
            List<String> vidList = new ArrayList<>();
            vidList.add("1");
            admin.setMylistvideo(vidList);
            userRepository.save(admin);
        } else{
            List<String> vidList = new ArrayList<>();
            vidList.add("1");
            admin.setMylistvideo(vidList);
            userRepository.save(admin);
        }
    }
}
