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
		//在这里我测试一下（仅加入该行）
        System.out.println(getBean(request,UserInfo.class));
        int resultCode = helloService.addUser(getBean(request,UserInfo.class));
        String rspInfo = "你好!" + userName + ",操作结果码=" + resultCode;
        //response.setHeader("Content-type","text/html;charset=UTF-8"); 
        //response.getOutputStream().write(rspInfo.getBytes("UTF-8"));
        //同事说这里有bug！！！！左找又找，左测试又测试，没有呀，鬼崽子，原来是同事自己调用写错了参数，好吧，看来是代码容错不行，针对同事的
        //这个错误，在这里判断下，如果他传入的参数有问题，我需要友好的告诉他那个参数出错了。
        //ok，解决了，提交上去，给他用吧！
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
