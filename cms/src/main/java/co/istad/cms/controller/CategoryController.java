package co.istad.cms.controller;

import co.istad.cms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String viewCategory(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "category/main";
    }

    @GetMapping("/form")
    public String viewCategoryForm() {
        return "category/form";
    }


}
