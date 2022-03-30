package io.muzoo.ssc.project.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    String findCommentByVideoIdAndUserId(long video_id, long user_id);

    String findCommentByVideoIdAndUserIdAndAndTime_stamp(long video_id, long user_id,
                                                         float time_stamp);

    List<Comment> findAllByVideo(Video video);

    List<Comment> findAllByVideoId(long video_id);

    List<Comment> findAllByComment(String comment);

    List<Comment> findAllByUser(User user);

    int deleteAllByUserId(long id);

    int deleteAllByVideoId(long id);

    int deleteByVideoIdAndUserIdAndTime_stamp(long video_id, long user_id, float time_stamp);




}
