package co.uk.yktech.article;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMSArticleRepository extends CrudRepository<CMSArticle, Long> {
	public CMSArticle findTopByOrderByIDDesc();
	public List<CMSArticle> findTop5ByOrderByIDDesc();
	public CMSArticle findByTitle(String title);
	
}
