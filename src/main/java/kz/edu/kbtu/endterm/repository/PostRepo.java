package kz.edu.kbtu.endterm.repository;

import kz.edu.kbtu.endterm.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
