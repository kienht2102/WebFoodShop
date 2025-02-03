package org.web.admin.controller;

import org.web.library.model.Category;
import org.web.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Quản lý thể loại");
        List<Category> categories = categoryService.findALl();
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.size());
        model.addAttribute("categoryNew", new Category());
        return "categories";
    }

    @PostMapping("/save-category")
    public String save(@ModelAttribute("categoryNew") Category category, Model model, RedirectAttributes redirectAttributes) {
        try {
            categoryService.save(category);
            model.addAttribute("categoryNew", category);
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Tên thể loại bị trùng lặp!");
        } catch (Exception e2) {
            e2.printStackTrace();
            model.addAttribute("categoryNew", category);
            redirectAttributes.addFlashAttribute("error",
                    "Lỗi hệ thống");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Optional<Category> findById(Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/update-category")
    public String update(Category category, RedirectAttributes redirectAttributes) {
        try {
            categoryService.update(category);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Tên thể loại bị trùng lặp!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi hệ thống hoặc trùng lặp tên thể loại!");
        }
        return "redirect:/categories";
    }


    @RequestMapping(value = "/delete-category", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Tên thể loại bị trùng lặp!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi hệ thống");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enable(Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.enableById(id);
            redirectAttributes.addFlashAttribute("success", "Khôi phục thành công");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Tên thể loại bị trùng lặp!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi hệ thống");
        }
        return "redirect:/categories";
    }
}
