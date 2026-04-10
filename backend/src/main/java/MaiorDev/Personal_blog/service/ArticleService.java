package MaiorDev.Personal_blog.service;
import MaiorDev.Personal_blog.Entity.ArticleEnity;
import MaiorDev.Personal_blog.util.GenericResponse;

public interface ArticleService {

    GenericResponse getAllArticles();
    GenericResponse getArticleById(Long id);
    GenericResponse createArticle(ArticleEnity article);
    GenericResponse editArticle(Long id, ArticleEnity article);
    GenericResponse deleteArticle(Long id);
}
