package com.namnd.bookingbe.repository;


import com.namnd.bookingbe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Modifying
    @Query("UPDATE Comment c SET  c.leftKey =  c.leftKey + :shiftBy WHERE c.leftKey > :startFrom")
    @Transactional
    void shiftLeftKeys(long startFrom, long shiftBy);

    @Modifying
    @Query("UPDATE Comment c SET c.rightKey = c.rightKey + :shiftBy WHERE c.rightKey > :startFrom")
    @Transactional
    void shiftRightKeys(long startFrom, long shiftBy);

    @Modifying
    @Query("UPDATE Comment c SET c.rightKey = c.rightKey + :shiftBy WHERE c.id = :parentId")
    @Transactional
    void updateParentRootRightKey(UUID parentId, long shiftBy);

}
