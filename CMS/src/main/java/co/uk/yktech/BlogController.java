package co.uk.yktech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.uk.yktech.article.ArticleHelperService;
import co.uk.yktech.article.CMSArticle;
import co.uk.yktech.article.CMSArticleRepository;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
@Autowired
CMSArticleRepository CMSArticleRepo;

@Autowired
ArticleHelperService articleHelperService;
	
	@GetMapping("/")
	public String home(Model theModel) {
		Iterable<CMSArticle> articles = CMSArticleRepo.findAll();
		theModel.addAttribute("categoryLinks", articleHelperService.getAllCategories());
		theModel.addAttribute("tagLinks", articleHelperService.getAllTagLinks());
		theModel.addAttribute("articles", articles);
		return "blog/blog-home";
	}

}
