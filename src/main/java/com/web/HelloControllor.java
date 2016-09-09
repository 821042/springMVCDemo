package com.web;

import com.model.UserInfo;
import com.service.IHelloService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class HelloControllor {
    @Resource
    private IHelloService helloService;

    @RequestMapping("/hello.do")
    @ResponseBody 
    public String sayHello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        //试试看
        System.out.println(getBean(request,UserInfo.class).getUser_name());
        int resultCode = helloService.addUser(getBean(request,UserInfo.class).getUser_name());
        String rspInfo = "你好!" + userName + ",操作结果码=" + resultCode;
        //response.setHeader("Content-type","text/html;charset=UTF-8"); 
        //response.getOutputStream().write(rspInfo.getBytes("UTF-8"));
        return "NewFile";
    }
    
    @RequestMapping("/home.do")
    public String showHomePage(Model model){
    	model.addAttribute("name","spring-mvc这里是中文哟");
    	return "home";
    }
    
    @RequestMapping(value="/home1.do")
    public ModelAndView showHomePage1(ModelAndView mv){
    	mv.addObject("name","spring-mvc这里是中文哟");
    	mv.setViewName("home");
    	return mv;
    }
    
    protected <T> T getBean(HttpServletRequest request, Class<T> c) {
    	try {
    	T obj = c.newInstance();
    	BeanUtils.copyProperties(obj, getBean(request));
    	return obj;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    	}


    	protected Map getBean(HttpServletRequest request) {
    	Map bm = new HashMap();
    	Map<String, String[]> tmp = request.getParameterMap();
    	if (tmp != null) {
    	for (String key : tmp.keySet()) {
    	String[] values = tmp.get(key);
    	bm.put(key, values.length == 1 ? values[0].trim() : values);
    	}
    	}
    	return bm;
    	}
}
