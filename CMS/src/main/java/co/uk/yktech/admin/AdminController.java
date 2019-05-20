package co.uk.yktech.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.uk.yktech.UploadService;
import co.uk.yktech.article.ArticleHelperService;
import co.uk.yktech.article.CMSArticle;
import co.uk.yktech.article.CMSArticleRepository;
import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;
import co.uk.yktech.page.PageHelperService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	CMSPageRepository CMSPageRepo;
	@Autowired
	CMSArticleRepository CMSArticleRepo;

	@Autowired
	ArticleHelperService articleHelper;
	
	@Autowired
	PageHelperService pageHelper;

	@Autowired
	UploadService uploadService;

	@GetMapping("/")
	public String home() {
		return "admin/admin-homepage";
	}

	@GetMapping("/pages")
	public String showPages(Model theModel) {
		Iterable<CMSPage> pages = CMSPageRepo.findAllByOrderByPageOrderAsc();
		theModel.addAttribute("pages", pages);
		return "admin/pages";
	}

	@GetMapping("/editPage/{ID}")
	public String editPage(@PathVariable Long ID, Model theModel) {
		logger.info("Loading page for editing with ID == " + ID);
		try {
			CMSPage page = CMSPageRepo.findById(ID).get();
			theModel.addAttribute("page", page);
		} catch (IndexOutOfBoundsException e) {
			logger.warn("Page Not Found");
		}
		return "admin/editpage";
	}
	@GetMapping("/editPage/priority/{ID}")
	public String priority(@PathVariable Long ID, Model theModel, @RequestParam String priority) {
		if(priority.equals("up")) {
			pageHelper.adjustPageUp(ID);
			logger.info("pressed up");
		} else {
			pageHelper.adjustPageDown(ID);
			logger.info("pressed down");
		}
		
		return "redirect:/admin/pages";
	}
	
	@GetMapping("/editPage/newPage")
	public String newPage(Model theModel) {
		CMSPage page = new CMSPage();
		theModel.addAttribute("page", page);
		return "admin/editpage";
	}

	@PostMapping("/editPage/savePage")
	public String savePage(@RequestParam("file") MultipartFile file, @ModelAttribute("page") CMSPage page) {
		if (!file.isEmpty()) {
			// Set extension within a String
			String fileName = file.getOriginalFilename();
			String extension = fileName.substring(fileName.indexOf("."));
			fileName = page.getPageTitle().replaceAll("\\s", "") + extension;
			page.setHeaderImage("/uploads/" + fileName);
			try {
				uploadService.uploadFile(file, fileName);
			} catch (Exception e) {

			}
		}
		CMSPageRepo.save(page);
		return "redirect:/admin/editPage/" + page.getID();
	}

	@GetMapping("/articles")
	public String showArticles(Model theModel) {
		Iterable<CMSArticle> articles = CMSArticleRepo.findAll();
		theModel.addAttribute("articles", articles);
		return "admin/articles";
	}

	@GetMapping("/editArticle/{articleID}")
	public String editArticle(@PathVariable Long articleID, Model theModel) {
		logger.info("Loading article for editing with ID == " + articleID);

		CMSArticle article = CMSArticleRepo.findById(articleID).get();
		theModel.addAttribute("article", article);
		return "admin/editarticle";
	}

	@GetMapping("/editArticle/newArticle")
	public String editArticle(Model theModel) {
		CMSArticle article = new CMSArticle();
		theModel.addAttribute("article", article);
		return "admin/editarticle";
	}

	@PostMapping("/saveArticle")
	public String saveArticle(@RequestParam("tagString") String tagString,
			@ModelAttribute("article") CMSArticle theArticle) {

		if (theArticle.getID() == null)
			articleHelper.saveArticle(theArticle, tagString, true);
		else
			articleHelper.saveArticle(theArticle, tagString, false);

		return "redirect:/admin/editArticle/" + theArticle.getID();
	}

}
