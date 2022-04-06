package io.muzoo.ssc.project.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    User findFirstById(long id);
//    @Query("SELECT user FROM User user WHERE user.id =:user_id")
//    User findFirstById(  @Param("user_id") long user_id);

//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE User SET mylist = :myList where id = :id")
//    int updateMylistById(@Param(value = "id") long id,
//                         @Param(value = "myList") List<Video> myList);

    int deleteByUsername(String username);

//    @Modifying
//    @Query("UPDATE User SET timestamp = :timestamp where username = :username")
//    int updateTimestampByUsername(@Param(value = "username") String username, @Param(value = "timestamp") float timestamp);
}
