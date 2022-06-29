package com.kenit.login.controller;

import com.kenit.login.dao.UserDao;
import com.kenit.login.model.Login;
import com.kenit.login.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView home(){
         ModelAndView modelAndView = new ModelAndView("index","login",new Login());
         return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("login") Login login){
        User user = UserDao.checkLogin(login);
        ModelAndView modelAndView;
        if (user == null){
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("message","username or password not validation");
        }
        else {
            modelAndView = new ModelAndView("user");
            modelAndView.addObject("user",user);
        }
        return modelAndView;
    }
}
