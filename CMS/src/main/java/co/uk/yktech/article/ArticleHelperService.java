package co.uk.yktech.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		for (ArticleCategory category : categories) {
			categoryList.put(category.getCategoryName(), category.getArticles().size());
		}
		return categoryList;
	}

	public void saveArticle(CMSArticle theArticle, String tagString, boolean newArticle) {
		//set new article tags using helper
		theArticle.setArticleTags(updateTags(tagString, theArticle.getID(), newArticle));
		

		ArticleAuthorRepo.save(theArticle.getTheAuthor());
		ArticleCategoryRepo.save(theArticle.getTheCategory());
		CMSArticleRepo.save(theArticle);
	}

	public Set<ArticleTag> updateTags(String tagString, Long articleID, boolean newArticle) {
		// add and create new tags
		Set<ArticleTag> newTags = convertTagStringToSet(tagString);

		// remove tags if article exists
		if (!newArticle) {
			CMSArticle article = CMSArticleRepo.findById(articleID).get();
			Set<ArticleTag> oldSet = article.getArticleTags();
			oldSet.removeAll(newTags);
			oldSet.forEach(tg -> {
				if (tg.getArticles().size() == 1) {
					deleteTag(tg.getArticles().iterator().next(), tg);
				}
			});

		}

		return newTags;
	}

	public void deleteTag(CMSArticle article, ArticleTag tag) {
		Set<ArticleTag> newSet = article.getArticleTags();
		newSet.remove(tag);
		article.setArticleTags(newSet);
		CMSArticleRepo.save(article);
		ArticleTagRepo.delete(tag);
	}

	public ArticleTag newTag(String name) {
		ArticleTag newTag = new ArticleTag();
		newTag.setTagName(name);
		ArticleTagRepo.save(newTag);
		return newTag;
	}

	public Set<ArticleTag> convertTagStringToSet(String tagString) {
		Set<ArticleTag> newTags = new HashSet<>();
		String[] tags = tagString.split(",");

		for (int i = 0; i < tags.length; i++) {
			tags[i] = tags[i].trim();
			if (ArticleTagRepo.existsByTagName(tags[i])) {
				// if tag exists
				newTags.add(ArticleTagRepo.findByTagName(tags[i]));
			} else {
				// new tag
				newTags.add(newTag(tags[i]));
			}
		}
		return newTags;
	}

}
