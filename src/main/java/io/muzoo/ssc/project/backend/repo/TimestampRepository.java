package io.muzoo.ssc.project.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimestampRepository extends JpaRepository<Timestamp, Long> {

    Timestamp findFirstByUser_IdAndVideo_Id(long user_id, long video_id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Timestamp SET timestamp = :timestamp where user.id = :user_id AND video.id = :video_id")
    int updateTimestampByUser_IdAndVideo_Id(@Param(value = "user_id") long user_id, @Param(value = "video_id") long video_id,@Param(value = "timestamp") float timestamp);
}
