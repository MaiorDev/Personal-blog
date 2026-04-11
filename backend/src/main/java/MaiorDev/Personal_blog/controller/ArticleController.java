package MaiorDev.Personal_blog.controller;

import MaiorDev.Personal_blog.Entity.ArticleEnity;
import MaiorDev.Personal_blog.service.ArticleService;
import MaiorDev.Personal_blog.util.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public GenericResponse getAllArticles() {
        GenericResponse response = articleService.getAllArticles();
        return response.getCode().equals(0) ? ResponseEntity.ok(response).getBody() : ResponseEntity.status(HttpStatus.CONFLICT).body(response).getBody();
    }

    @GetMapping("/articles/{id}")
    public GenericResponse getArticleById(@PathVariable Long id) {
        GenericResponse response = articleService.getArticleById(id);
        return response.getCode().equals(0) ? ResponseEntity.ok(response).getBody() : ResponseEntity.status(HttpStatus.CONFLICT).body(response).getBody();
    }

    @PostMapping("/articles")
    public GenericResponse createArticle(@RequestBody ArticleEnity article) {
        GenericResponse response = articleService.createArticle(article);
        return response.getCode().equals(0) ? ResponseEntity.ok(response).getBody() : ResponseEntity.status(HttpStatus.CONFLICT).body(response).getBody();
    }

    @PutMapping("/articles/{id}")
    public GenericResponse editArticle(@PathVariable Long id, @RequestBody ArticleEnity article) {
        GenericResponse response = articleService.editArticle(id, article);
        return response.getCode().equals(0) ? ResponseEntity.ok(response).getBody() : ResponseEntity.status(HttpStatus.CONFLICT).body(response).getBody();
    }

    @DeleteMapping("/articles/{id}")
    public GenericResponse deleteArticle(@PathVariable Long id) {
        GenericResponse response = articleService.deleteArticle(id);
        return response.getCode().equals(0) ? ResponseEntity.ok(response).getBody() : ResponseEntity.status(HttpStatus.CONFLICT).body(response).getBody();
    }

}