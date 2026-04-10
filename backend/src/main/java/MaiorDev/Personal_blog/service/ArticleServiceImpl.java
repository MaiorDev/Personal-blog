package MaiorDev.Personal_blog.service;

import MaiorDev.Personal_blog.Entity.ArticleEnity;
import MaiorDev.Personal_blog.model.ArticleDTO;
import MaiorDev.Personal_blog.repository.ArticleRepository;
import MaiorDev.Personal_blog.util.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArticleServiceImpl implements ArticleService {

    ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public GenericResponse getAllArticles() {
        try {
            List<ArticleEnity> articles = articleRepository.findAll();
            List<ArticleDTO> dtos = articles.stream()
                    .map(entity -> new ArticleDTO(entity.getId(), entity.getTitle(), entity.getContent(), entity.getDate()))
                    .toList();
            return new GenericResponse(0, "Articles fetched successfully", dtos);
        } catch (Exception e) {
            return new GenericResponse(1, "Failed to fetch: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse getArticleById(Long id) {
        try {
            Optional<ArticleEnity> article = articleRepository.findById(id);
            return new GenericResponse(0, "Article fetched successfully", article);
        } catch (Exception e) {
            return new GenericResponse(1, "Failed to fetch articles: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse createArticle(ArticleEnity article) {
        try {
            ArticleEnity savedArticle = articleRepository.save(article);
            return new GenericResponse(0, "Article created successfully", savedArticle);
        } catch (Exception e) {
            return new GenericResponse(1, "Failed to create article: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse editArticle(Long id, ArticleEnity article) {
        try {
            return articleRepository.findById(id).map(existingArticle -> {
                existingArticle.setTitle(article.getTitle());
                existingArticle.setContent(article.getContent());
                ArticleEnity updated = articleRepository.save(existingArticle);
                ArticleDTO dto = new ArticleDTO(updated.getId(), updated.getTitle(), updated.getContent(), updated.getDate());
                return new GenericResponse(0, "Article edited successfully", dto);

            }).orElseGet(() ->
                    new GenericResponse(2, "Article with ID " + id + " not found", null)
            );

        } catch (Exception e) {
            return new GenericResponse(1, "Failed to edit article: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse deleteArticle(Long id) {
        try {
            articleRepository.deleteById(id);
            return new GenericResponse(0, "Article deleted successfully", null);
        } catch (Exception e) {
            return new GenericResponse(1, "Failed to delete article: " + e.getMessage(), null);
        }
    }
}
