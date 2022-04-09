package io.muzoo.ssc.project.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByVideo_Id(long video_id);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Comment WHERE user.id = :user_id")
    int deleteCommentByUser_Id(@Param(value = "user_id") long user_id);

    Comment findFirstByVideo_IdAndUser_IdAndTimestamp(long video_id, long user_id,
                                                float time_stamp);


}
