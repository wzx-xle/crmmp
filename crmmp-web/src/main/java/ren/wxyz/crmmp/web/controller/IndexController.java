/**
 * @Copyright 
 * @Project crmp-web
 * @Package wxyz.crmp.web.controller
 * @CreateTime 2016-2-23 下午8:35:44
 */
package ren.wxyz.crmmp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 首页控制器
 * </p>
 * @Author wxyz
 * @CreateTime 2016-2-23 下午8:35:44 init class
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/")
	public String index_() {
		return index();
	}
	
	@RequestMapping("/index")
	public String index() {
		return "redirect:/index.html";
	}
}
