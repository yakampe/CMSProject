package co.uk.yktech.article;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryRepository extends CrudRepository<ArticleCategory, Long>{
		public ArticleCategory findByCategoryName(String catName);
		public boolean existsByCategoryName(String catName);
}
