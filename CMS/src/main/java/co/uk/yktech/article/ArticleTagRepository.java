package co.uk.yktech.article;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagRepository extends CrudRepository<ArticleTag, Long>{
	 public ArticleTag findByTagName(String tagName);
	 public boolean existsByTagName(String tagName);
}
