/**
 * @Copyright 
 * @Project crmp-web
 * @Package wxyz.crmp.web.controller
 * @CreateTime 2016-2-23 下午8:57:59
 */
package wxyz.crmp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 菜单视图
 * </p>
 * @Author wxyz
 * @CreateTime 2016-2-23 下午8:57:59 init class
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@RequestMapping("nav")
	public String getNav() {
		return "";
	}
}
