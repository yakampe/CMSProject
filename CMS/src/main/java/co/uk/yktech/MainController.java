package co.uk.yktech;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.uk.yktech.article.ArticleAuthor;
import co.uk.yktech.article.ArticleAuthorRepository;
import co.uk.yktech.article.ArticleCategory;
import co.uk.yktech.article.ArticleCategoryRepository;
import co.uk.yktech.article.ArticleTag;
import co.uk.yktech.article.ArticleTagRepository;
import co.uk.yktech.article.CMSArticle;
import co.uk.yktech.article.CMSArticleRepository;
import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

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

	@GetMapping("/")
	public String home(Model theModel) {
		Iterable<CMSPage> pages = CMSPageRepo.findAll();
		theModel.addAttribute("pages", pages);
		return "homepage";
	}

	@GetMapping("/add")
	public String testAdd() {

		String content = "<div id=\"lipsum\">\r\n" + "<p>\r\n"
				+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras varius porttitor nisi sit amet tempor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean elementum diam felis, ut mollis mauris hendrerit non. Suspendisse fringilla sapien bibendum, eleifend purus eu, consequat est. Nulla ac interdum lectus. Nullam ipsum ipsum, placerat sit amet lectus ut, fringilla tristique dolor. Vestibulum eleifend quam sit amet orci fringilla dictum. Maecenas leo felis, mollis finibus egestas semper, gravida a nulla. Phasellus varius purus lobortis mattis aliquet. Aliquam mattis ornare egestas. Quisque fringilla est nec purus cursus semper. Proin fermentum ligula ac dignissim tincidunt. Pellentesque at convallis velit, eu placerat urna. Sed eget justo eu sem tincidunt ullamcorper.\r\n"
				+ "</p>\r\n" + "<p>\r\n"
				+ "Nunc sit amet vestibulum justo. Maecenas libero arcu, porttitor in porta sit amet, volutpat vitae ante. Pellentesque dapibus efficitur ultrices. Nulla faucibus porttitor nisi a scelerisque. Integer ac lectus lorem. Duis consectetur tortor orci, et accumsan eros tempor a. Aenean vel enim nec ipsum volutpat pulvinar eu sit amet nunc. Nulla sit amet metus sit amet eros commodo fermentum nec at eros. Nulla quis sem massa.\r\n"
				+ "</p>\r\n" + "<p>\r\n"
				+ "Sed vel neque suscipit metus ultrices malesuada vitae nec ante. Aenean porttitor, tortor at accumsan ornare, sem neque suscipit lacus, sit amet rutrum mauris eros eu turpis. Nullam malesuada molestie tempus. Nunc laoreet varius imperdiet. Phasellus sagittis eleifend varius. Cras suscipit, neque sed congue auctor, eros urna suscipit nisi, non fringilla nibh odio quis leo. Mauris blandit ex quam, quis maximus nulla commodo sed. Cras venenatis dui mi, convallis vulputate quam auctor et. Suspendisse sodales tellus sit amet ligula pulvinar, rhoncus molestie diam dapibus. Suspendisse tincidunt eget dolor tempus vulputate. Curabitur at viverra massa. Duis venenatis nisl lacus, nec volutpat felis efficitur ut. Cras molestie eu dui blandit consequat. Ut consectetur diam pretium ex lobortis, ut placerat nunc mattis.\r\n"
				+ "</p>\r\n" + "<p>\r\n"
				+ "Pellentesque eget pretium sem. Vivamus molestie, risus vel sollicitudin iaculis, massa tellus facilisis ante, laoreet sagittis dui leo at augue. Sed ultrices lobortis urna, vitae lobortis lectus aliquet posuere. Duis interdum feugiat neque ac viverra. Praesent auctor, felis ut ornare iaculis, purus nibh scelerisque mauris, porttitor ultrices nulla metus ut diam. Vivamus porttitor erat in eros cursus bibendum. Praesent pretium varius eleifend.\r\n"
				+ "</p>\r\n" + "<p>\r\n"
				+ "Etiam maximus ornare eros ut varius. Maecenas interdum justo nisi, sit amet feugiat libero eleifend vitae. In id erat feugiat, vehicula quam a, lobortis massa. Suspendisse convallis risus eu dui egestas congue. Quisque dictum suscipit metus scelerisque tempor. Vivamus sit amet pellentesque felis. In et nisi efficitur, porta nunc eget, suscipit enim. Phasellus ornare dui quis pretium dictum. In luctus nunc sed magna sodales sagittis. Donec eros enim, vestibulum sed dui a, tempus finibus urna. Donec vitae nulla consequat, fringilla mi in, aliquet est. Vestibulum placerat condimentum ante ac ultrices. Cras consectetur, lectus nec porta consequat, urna quam feugiat nisl, at ultricies urna velit in urna. Fusce sed justo eget leo posuere suscipit id nec enim. Nam iaculis est lacinia augue congue faucibus.\r\n"
				+ "</p></div>";
		for (int i = 0; i < 5; i++) {
			CMSPage c = new CMSPage();
			c.setContent(content);
			c.setPageTitle("Page " + i);
			c.setPageOrder(i + 1);
			c.setDisplayOnMainPage(true);
			CMSPageRepo.save(c);
		}

		ArticleTag b = new ArticleTag();
		ArticleTag b1 = new ArticleTag();
		ArticleTag b2 = new ArticleTag();
		ArticleTag b3 = new ArticleTag();
		ArticleTag b4 = new ArticleTag();
		b.setTagName("Tag Name");
		b1.setTagName("TN1");
		b2.setTagName("TN2");
		b3.setTagName("TN3");
		b4.setTagName("single");
		ArticleTagRepo.save(b);
		ArticleTagRepo.save(b1);
		ArticleTagRepo.save(b2);
		ArticleTagRepo.save(b3);
		ArticleTagRepo.save(b4);

		for (int i = 0; i < 5; i++) {
			CMSArticle a = new CMSArticle();

			Set<ArticleTag> bs = new HashSet<>();

			for (ArticleTag ea : ArticleTagRepo.findAll()) {
				bs.add(ea);
			}
			if (i > 0)
				bs.remove(b4);
			ArticleAuthor c = new ArticleAuthor();
			c.setDisplayName("Author Name");

			ArticleCategory d = new ArticleCategory();
			d.setCategoryName("Category Name" + i);

			a.setTitle("Article Title");
			a.setArticleTags(bs);
			a.setTheCategory(d);
			a.setTheAuthor(c);
			a.setContent(content);
			LocalDate now = LocalDate.now();
			a.setDatePosted(now);

			ArticleAuthorRepo.save(c);
			ArticleCategoryRepo.save(d);

			CMSArticleRepo.save(a);
		}

		return "forward:/";
	}

}
