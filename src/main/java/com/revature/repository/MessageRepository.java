package com.revature.repository;

import com.revature.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author William Gentry
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
