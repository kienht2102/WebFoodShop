package org.web.customer.controller;

import org.web.library.dto.CustomerDto;
import org.web.library.model.Customer;
import org.web.library.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final CustomerService customerService;
    private final BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Trang đăng nhập");
        model.addAttribute("page", "Trang chủ");
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Đăng ký");
        model.addAttribute("page", "Đăng ký");
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }


    @PostMapping("/do-register")
    public String registerCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                   BindingResult result,
                                   Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
            String username = customerDto.getUsername();
            Customer customer = customerService.findByUsername(username);
            if (customer != null) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("error", "Email đã được đăng ký!");
                return "register";
            }
            if (customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                customerService.save(customerDto);
                model.addAttribute("success", "Đăng ký thành công!");
            } else {
                model.addAttribute("error", "Sai mật khẩu");
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Hệ thống đang lỗi, xin hãy thử lại sau!");
        }
        return "register";
    }


}
