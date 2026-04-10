package MaiorDev.Personal_blog.repository;

import MaiorDev.Personal_blog.Entity.ArticleEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEnity, Long> {
}