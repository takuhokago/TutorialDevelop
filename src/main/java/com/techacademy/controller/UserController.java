package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("userlist", service.getUserList());
        return "user/list";
    }

    /** User登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        return "user/register";
    }

    /** User登録処理 */
    @PostMapping("/register")
    public String postRegister(User user) {
        service.saveUser(user);
        return "redirect:/user/list";
    }

    /** User更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user/update";
    }

    /** User更新処理 */
    @PostMapping("/update/{id}/")
    public String postUser(User user) {
        service.saveUser(user);
        return "redirect:/user/list";
    }
}