package co.uk.yktech.article;

import java.time.LocalDate;
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
import org.springframework.ui.Model;

import co.uk.yktech.page.CMSPageRepository;

@Service
public class ArticleHelperService {

	Logger logger = LoggerFactory.getLogger(ArticleHelperService.class);

	@Autowired
	CMSPageRepository CMSPageRepo;
	
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
		// set new article tags using helper
		theArticle.setArticleTags(updateTags(tagString, theArticle.getID(), newArticle));
		if (theArticle.getDatePosted() == null)
			theArticle.setDatePosted(LocalDate.now());
		theArticle.setTheCategory(createOrUpdateCategory(theArticle.getTheCategory().getCategoryName()));
		

		ArticleAuthorRepo.save(theArticle.getTheAuthor());
		CMSArticleRepo.save(theArticle);
	}
	
	public ArticleCategory createOrUpdateCategory(String catName) {
		ArticleCategory newCat = new ArticleCategory();
		if(ArticleCategoryRepo.existsByCategoryName(catName)) {
			newCat = ArticleCategoryRepo.findByCategoryName(catName);
		} else {
			newCat.setCategoryName(catName);
		}
		ArticleCategoryRepo.save(newCat);
		return newCat;
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
	
	public Model blogPageLinks(Model theModel) {
		theModel.addAttribute("newestArticles", CMSArticleRepo.findTop5ByOrderByIDDesc());
		theModel.addAttribute("pages", CMSPageRepo.findAll());
		theModel.addAttribute("categoryLinks", getAllCategories());
		theModel.addAttribute("tagLinks", getAllTagLinks());
		return theModel;
	}

}
