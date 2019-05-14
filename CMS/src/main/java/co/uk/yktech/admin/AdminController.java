package co.uk.yktech.admin;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.uk.yktech.UploadService;
import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	CMSPageRepository CMSPageRepo;

	@Autowired
	UploadService uploadService;

	@GetMapping("/home")
	public String home() {
		return "admin-homepage";
	}

	@GetMapping("/pages")
	public String showPages(Model theModel) {
		Iterable<CMSPage> pages = CMSPageRepo.findAll();
		theModel.addAttribute("pages", pages);
		return "admin/pages";
	}

	@GetMapping("/editPage/{pageTitle}")
	public String editPage(@PathVariable String pageTitle, Model theModel) {
		logger.info("Loading page for editing with title == " + pageTitle);
		try {
			CMSPage page = CMSPageRepo.findByPageTitle(pageTitle).get(0);
			theModel.addAttribute("page", page);
		} catch (IndexOutOfBoundsException e) {
			logger.warn("Page Not Found");
		}
		return "admin/editpage";
	}

	@PostMapping("/savePage")
	public String savePage(@RequestParam("file") MultipartFile file, @ModelAttribute("page") CMSPage page) {
		if(!file.isEmpty()) {
			// Set extension within a String
			String fileName = file.getOriginalFilename();
			String extension = fileName.substring(fileName.indexOf("."));
			fileName = page.getPageTitle().replaceAll("\\s" , "")+extension;
			page.setHeaderImage("/uploads/"+fileName);
			try {
				uploadService.uploadFile(file, fileName);
			} catch (Exception e) {
				
			}
		}
		CMSPageRepo.save(page);
		// logger.info("Saving page with ID ==" +page.getID());
		return "admin-homepage";
	}


}
