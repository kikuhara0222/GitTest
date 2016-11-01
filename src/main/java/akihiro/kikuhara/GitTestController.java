package akihiro.kikuhara;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GitTestController {
	
	@RequestMapping("/")
	public String index(Model model){
		ReadExcel read = new ReadExcel();
		try{
			model.addAttribute("msg",read.readExcel());
		}catch(Exception e){
			model.addAttribute("msg","エラーです");
		}
		return "index";
	}
	
}
