package com.senla.database.repository;

import com.senla.database.entity.AdvComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvCommentRepository extends JpaRepository<AdvComment, Long> {
}
