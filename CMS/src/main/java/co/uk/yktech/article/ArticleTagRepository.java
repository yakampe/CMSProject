package co.uk.yktech.article;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagRepository extends CrudRepository<ArticleTag, Long>{

}
