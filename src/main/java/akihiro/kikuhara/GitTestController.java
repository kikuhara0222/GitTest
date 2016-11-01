package akihiro.kikuhara;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GitTestController {
	
	@Autowired
	private Properties properties;

	// indexページに遷移する
	@RequestMapping("/index")
	public String index(@RequestParam(defaultValue = "") String msg, Model model) {
		model.addAttribute("msg", msg);
		return "index";
	}
	
	// リサイズ処理
	@RequestMapping("/resize")
	public String reSize(@RequestParam MultipartFile uploadImg, RedirectAttributes redirectAttributes, Model model) {
		String outputPath = properties.getOutPath();
		
		try {
			InputStream is = uploadImg.getInputStream();
			BufferedImage img = ImageIO.read(is);
			ResizeSample res = new ResizeSample();
			String outPutName = outputPath + "resize_Image.jpg";
			System.out.println(outPutName);
			res.resizeByScaledInstance(img, outPutName, 300, 300);
			
			System.out.println("End");
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addAttribute("msg", "エラーが発生しました");
			return "redirect:/index";
		}
		model.addAttribute("filePath", "resize_Image.jpg");
		return "newimage";

	}
}
