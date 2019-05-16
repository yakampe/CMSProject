package co.uk.yktech.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleHelperService {

	Logger logger = LoggerFactory.getLogger(ArticleHelperService.class);

	
	@Autowired
	CMSArticleRepository CMSArticleRepo;

	@Autowired
	ArticleTagRepository ArticleTagRepo;

	@Autowired
	ArticleAuthorRepository ArticleAuthorRepo;

	@Autowired
	ArticleCategoryRepository ArticleCategoryRepo;

	public List<String> getAllTagLinks() {
		List<String> articleTagLinks = new ArrayList<>();
		Iterable<ArticleTag> tags = ArticleTagRepo.findAll();
		for (ArticleTag tag : tags) {

			articleTagLinks.add(tag.getTagName());
		}
		return articleTagLinks;
	}

	public Map<String, Integer> getAllCategories() {
		logger.info("Initialising ArticleHelper -- getAllCategories()");
		Map<String, Integer> categoryList = new HashMap<>();
		Iterable<ArticleCategory> categories = ArticleCategoryRepo.findAll();
		for(ArticleCategory category:categories) {
			categoryList.put(category.getCategoryName(), category.getArticles().size());
		}
		return categoryList;
	}
	
	public void saveArticle(CMSArticle theArticle) {
		for(ArticleTag eachTag :theArticle.getArticleTags()) {
			ArticleTagRepo.save(eachTag);
		}
		ArticleAuthorRepo.save(theArticle.getTheAuthor());
		ArticleCategoryRepo.save(theArticle.getTheCategory());
		CMSArticleRepo.save(theArticle);
	}

}
