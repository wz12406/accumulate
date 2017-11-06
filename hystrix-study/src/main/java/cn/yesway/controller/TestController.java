package cn.yesway.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yesway.entity.User;
import cn.yesway.service.UserService;

import com.fasterxml.jackson.databind.util.JSONPObject;

//@RequestMapping可以出现在类级别上，也可以出现在方法上。 
//如果出现在类级别上，那请求的 url 为 类级别 上的 @RequestMapping + 方法级别上的 @RequestMapping
//否则直接取方法级上的@RequestMapping
@Controller
@RequestMapping(value = "/test")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	// 自动注入userService
	@Autowired
	private UserService userService;

	// 匹配url为/test/login，请求方法为get，返回页面test.jsp，
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// 输入页面参数loginName=admin
		model.addAttribute("loginName", "admin");

		return "test";
	}
	
	// 匹配url为/test/login，请求方法为get，返回页面test.jsp，
		@RequestMapping(value = "/tt", method = RequestMethod.GET)
		public String tt(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
			
			return "tt";
		}

	// 匹配url为/test/login，请求方法为post，从页面获取参数userName,password返回页面test.jsp
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validUser(String loginName, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("login {loginName:" + loginName + ",password:" + password + "}");
		User user = userService.getUserByLoginName(loginName);
		if (user != null && user.getPassword().equals(password)) {
			model.put("message", "登录成功");
		} else {
			model.put("message", "用户验证失败");
		}
		model.addAttribute("loginName", loginName);
		return "test";
	}

	// 匹配url为/test/story/*，将请求定位到页面/story/*.jsp
	@RequestMapping(value = "/story/{page}")
	public String story(@PathVariable("page") String page) {
		return "story/" + page;
	}

	// 匹配url为/test/login2，将请求重新定位/test/login
	// 使用redirectAttributes传递参数
	@RequestMapping(value = "/login2")
	public String login2(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "重定向");
		return "redirect:/test/login";
	}

	// 匹配url为/test/list，请求方法为 POST
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String getList() {
		return null;
	}

	// 匹配url为/test，请求方法为 GET或 POST，且具有 hello参数，且值为 world的请求才能匹配到该方法
	// 如： /test?hello=world
	@RequestMapping(params = "hello=world", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloworld() {
		return null;
	}

	// 匹配url为/test，请求方法为 GET，且具有请求参数 java 即匹配此方法，而不管 java 参数的值是什么，
	// 如：/test?java=anything 
	@RequestMapping(params = "java", method = { RequestMethod.GET })
	public String java() {
		return null;
	}

	// 匹配url为/test，请求方法为 POST，且请求的Header中具有请求参数 hello，且值为 world的请求才能匹配到该方法
	@RequestMapping(headers = "hello=world", method = { RequestMethod.POST })
	public String cplusplus() {
		return null;
	}

	// 匹配url为 /test/test，请求参数中一定有名为 userId 的参数，且值为整数
	// 如果没有传递 userId 参数，框架会传入 null，因定义为int所以会产生异常，要避免这个问题，可将参数改为Integer
	// 如果传入参数 userId为非整型，则会产生异常
	@RequestMapping("/test")
	public String test(int userId) {
		return null;
	}

	// 匹配url为 /test/test2
	// 参数userId绑定到新的名称为 id上
	// 参数age绑定到age上，使用defaultValue = "-1"，如果没有传入值会使用默认值-1
	// 参数user，需要传入对象中的属性名称，如loginName=aa&name=b&password=c
	@RequestMapping("/test2")
	public String test2(@RequestParam("userId") int id, @RequestParam(defaultValue = "-1") Integer age, User user) {
		System.out.println("id:" + id + ",age:" + age + ",user:" + user);
		return null;
	}

	// 请求的 url 必须满足类似 /test/user/zhangsan/18 这样的格式才能匹配方法
	// @PathVariable 和 @RequestParam 的区别在于：
	// @PathVariable 的 url：/test/user/zhangsan/18
	// @RequestParam 的 url：/test/user?nickname=zhangsan&age=18
	@RequestMapping("/user/{nickname}/{age}")
	public String getUserInfo(@PathVariable("nickname") String name, @PathVariable int age) {
		return null;
	}

	// 匹配url 必须为 /test/body
	// 将请求的request body转换成string绑定到body
	@RequestMapping("/body")
	public String getBody(@RequestBody String body) {
		logger.debug(body);
		return null;
	}

	// 匹配url 必须为 /test/user/json，@ResponseBody 注解将user对象转成json返回
	@RequestMapping("/user/json")
	public @ResponseBody
	User getUser() {
		User user = new User();
		user.setLoginName("admin");
		user.setFullName("管理员");
		user.setPassword("admin");
		user.setAddTime(new Date());
		return user;
	}

	// 匹配url 为/test/json
	// 返回内容为json数据{"loginName":"anyonghong","name":"安永洪","password":"password"}
	@RequestMapping("/json")
	public @ResponseBody
	Map<String, Object> getJson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", "anyonghong");
		map.put("name", "安永洪");
		map.put("password", "password");
		return map;
	}

	// 匹配url为/test/jsonp
	// 返回jsonp数据，如jquery1111({"pi":"hello,I'm Fjt"})
	@RequestMapping(value = "/jsonp")
	@ResponseBody
	public JSONPObject getJsonp(ModelMap map, @RequestParam String callback) {
		map.put("pi", "hello,I'm Fjt");
		return new JSONPObject(callback, map);
	}

	// 匹配url 为 /test/text，返回内容为body中hello
	// 为解决返回中文乱码问题，解决方式1 添加：produces = {"application/json;charset=UTF-8"}
	// 或者text/javascript;charset=UTF-8
	// @RequestMapping(value = "/text", produces =
	// {"application/json;charset=UTF-8"})
	// 解决方法2：配置spring-mvc.xml mvc:annotation-driven节点，前提是spring 3.1
	@RequestMapping(value = "/text")
	public @ResponseBody
	String getText() {
		return "安永洪";
	}

	// 匹配url 为 /test/text1，返回内容为body中hello
	@RequestMapping("/text1")
	public void getText1(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("hello");
		} catch (IOException e) {
			logger.error("getText1 error:", e);
		}
	}
}
