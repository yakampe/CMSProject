package co.uk.yktech.article;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleAuthorRepository extends CrudRepository<ArticleAuthor, Long> {

}
