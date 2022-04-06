package io.muzoo.ssc.project.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByVideo_Id(long video_id);


    Comment findFirstByVideo_IdAndUser_IdAndTimestamp(long video_id, long user_id,
                                                float time_stamp);


}
