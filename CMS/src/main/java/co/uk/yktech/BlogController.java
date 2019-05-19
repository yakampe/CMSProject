package co.uk.yktech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.uk.yktech.article.ArticleCategory;
import co.uk.yktech.article.ArticleCategoryRepository;
import co.uk.yktech.article.ArticleHelperService;
import co.uk.yktech.article.ArticleTag;
import co.uk.yktech.article.ArticleTagRepository;
import co.uk.yktech.article.CMSArticle;
import co.uk.yktech.article.CMSArticleRepository;
import co.uk.yktech.page.CMSPageRepository;

@Controller
@RequestMapping("/blog")
public class BlogController {

	Logger logger = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	CMSArticleRepository CMSArticleRepo;

	@Autowired
	ArticleCategoryRepository ArticleCategoryRepo;

	@Autowired
	ArticleTagRepository ArticleTagRepo;

	@Autowired
	CMSPageRepository CMSPageRepo;

	@Autowired
	ArticleHelperService articleHelperService;

	@GetMapping("/")
	public String home(Model theModel) {
		theModel = articleHelperService.blogPageLinks(theModel);

		Iterable<CMSArticle> articles = CMSArticleRepo.findAll();
		theModel.addAttribute("articles", articles);
		return "blog/blog-home";
	}

	@GetMapping("/articles/{articleName}")
	public String articles(@PathVariable("articleName") String articleName, Model theModel) {
		theModel = articleHelperService.blogPageLinks(theModel);
		
		CMSArticle art = CMSArticleRepo.findByTitle(articleName);
		theModel.addAttribute("articles", art);
		return "blog/blog-home";
	}
	
	@GetMapping("/tags/{tagName}")
	public String tags(@PathVariable("tagName") String tagName, Model theModel) {
		theModel = articleHelperService.blogPageLinks(theModel);

		ArticleTag tag = ArticleTagRepo.findByTagName(tagName);
		theModel.addAttribute("articles", tag.getArticles());
		return "blog/blog-home";
	}

	@GetMapping("/category/{catName}")
	public String categories(@PathVariable("catName") String catName, Model theModel) {
		theModel = articleHelperService.blogPageLinks(theModel);

		ArticleCategory cat = ArticleCategoryRepo.findByCategoryName(catName);
		theModel.addAttribute("articles", cat.getArticles());
		return "blog/blog-home";
	}

}
