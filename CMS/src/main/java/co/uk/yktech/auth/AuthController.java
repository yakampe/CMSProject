package co.uk.yktech.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {

	@RequestMapping("/")
	public String login(Model theModel) {
		return "login";
	}
}
