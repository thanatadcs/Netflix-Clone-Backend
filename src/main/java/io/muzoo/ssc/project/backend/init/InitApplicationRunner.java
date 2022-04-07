package io.muzoo.ssc.project.backend.init;

import io.muzoo.ssc.project.backend.repo.*;
import io.muzoo.ssc.project.backend.tags.TagEnum;
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
            sample1.setTags(TagEnum.ROMANCE.getTag());
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
            sample2.setTags(TagEnum.FANTASY.getTag());
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
            sao_ep1.setTags(TagEnum.ROMANCE.getTag() + ", " + TagEnum.ACTION.getTag() + ", " + TagEnum.ANIME.getTag());
            videoRepository.save(sao_ep1);
        }
        else if (sao_ep1.getDescription() == null) {
            sao_ep1.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep1);
        }
        Video sao_ep2 = videoRepository.findFirstByFilename("SAO_ep2");
        if (sao_ep2 == null) {
            sao_ep2 = new Video();
            sao_ep2.setFilename("SAO_ep2");
            sao_ep2.setTitle("SAO_ep2");
            sao_ep2.setLink("http://157.245.155.41:8082/hls/SAO_EP2.mp4/index.m3u8");
            sao_ep2.setThumbnail("https://i.imgur.com/H37WZU7.png");
            sao_ep2.setDescription("Sword Art Online... I didnt watch any episode yet...");
            sao_ep2.setTags(TagEnum.ROMANCE.getTag() + ", " + TagEnum.ACTION.getTag() + ", " + TagEnum.ANIME.getTag());
            videoRepository.save(sao_ep2);
        }
        else if (sao_ep2.getDescription() == null) {
            sao_ep2.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep2);
        }
        Video sao_ep3 = videoRepository.findFirstByFilename("SAO_ep3");
        if (sao_ep3 == null) {
            sao_ep3 = new Video();
            sao_ep3.setFilename("SAO_ep3");
            sao_ep3.setTitle("SAO_ep3");
            sao_ep3.setLink("http://157.245.155.41:8082/hls/SAO_EP3.mp4/index.m3u8");
            sao_ep3.setThumbnail("https://i.imgur.com/wlJtkcZ.png");
            sao_ep3.setDescription("Sword Art Online... I didnt watch any episode yet...");
            sao_ep3.setTags(TagEnum.ROMANCE.getTag() + ", " + TagEnum.ACTION.getTag() + ", " + TagEnum.ANIME.getTag());
            videoRepository.save(sao_ep3);
        }
        else if (sao_ep3.getDescription() == null) {
            sao_ep3.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep3);
        }
        Video sao_ep4 = videoRepository.findFirstByFilename("SAO_ep4");
        if (sao_ep4 == null) {
            sao_ep4 = new Video();
            sao_ep4.setFilename("SAO_ep4");
            sao_ep4.setTitle("SAO_ep4");
            sao_ep4.setLink("http://157.245.155.41:8082/hls/SAO_EP4.mp4/index.m3u8");
            sao_ep4.setThumbnail("https://i.imgur.com/iKLU8BY.png");
            sao_ep4.setDescription("Sword Art Online... I didnt watch any episode yet...");
            sao_ep4.setTags(TagEnum.ROMANCE.getTag() + ", " + TagEnum.ACTION.getTag() + ", " + TagEnum.ANIME.getTag());
            videoRepository.save(sao_ep4);
        }
        else if (sao_ep4.getDescription() == null) {
            sao_ep4.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep4);
        }
        Video sao_ep5 = videoRepository.findFirstByFilename("SAO_ep5");
        if (sao_ep5 == null) {
            sao_ep5 = new Video();
            sao_ep5.setFilename("SAO_ep5");
            sao_ep5.setTitle("SAO_ep5");
            sao_ep5.setLink("http://157.245.155.41:8082/hls/SAO_EP5.mp4/index.m3u8");
            sao_ep5.setThumbnail("https://cdn.discordapp.com/attachments/910547173844869133/961680315678609438/SAO_EP5_thumbnail.png");
            sao_ep5.setDescription("Sword Art Online... I didnt watch any episode yet...");
            sao_ep5.setTags(TagEnum.ROMANCE.getTag() + ", " + TagEnum.ACTION.getTag() + ", " + TagEnum.ANIME.getTag());
            videoRepository.save(sao_ep5);
        }
        else if (sao_ep5.getDescription() == null) {
            sao_ep5.setDescription("Sword Art Online... I didnt watch any episode yet...");
            videoRepository.save(sao_ep5);
        }


        Comment sample1_comment = commentRepository.findFirstByVideo_IdAndUser_IdAndTimestamp(1, 1, 0);
        if (sample1_comment == null) {
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
        } else {
            List<String> vidList = new ArrayList<>();
            vidList.add("1");
            admin.setMylistvideo(vidList);
            userRepository.save(admin);
        }
    }
}
