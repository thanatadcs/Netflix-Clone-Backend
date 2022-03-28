package io.muzoo.ssc.project.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimestampRepository extends JpaRepository<Timestamp, Long> {

    Timestamp findFirstByUser_IdAndVideo_Id(long user_id, long video_id);
}
