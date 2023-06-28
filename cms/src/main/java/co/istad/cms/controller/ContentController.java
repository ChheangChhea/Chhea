package co.istad.cms.controller;

import co.istad.cms.model.Content;
import co.istad.cms.service.CategoryService;
import co.istad.cms.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final CategoryService categoryService;

    @GetMapping
    public String viewContent(ModelMap modelMap) {
        modelMap.addAttribute("contents", contentService.findContents());
        return "content/main";
    }

    @GetMapping("/form")
    public String viewContentForm(Content content, ModelMap modelMap) {

        modelMap.addAttribute("categories", categoryService.findCategories());
        modelMap.addAttribute("content", content);
        modelMap.addAttribute("isEditAction", false);

        return "content/form";
    }

    @PostMapping
    public String saveContent(Content content,
                              @RequestParam("isEditAction") Boolean isEditAction,
                              @RequestParam("fileThumbnail") MultipartFile thumbnail

                              ) {
        System.out.println(content);
        System.out.println(content.getCategory().getId());
        System.out.println(thumbnail.getOriginalFilename());

        System.out.println(isEditAction);

        if(isEditAction){
            contentService.editContentById(content);
        }else {
            contentService.saveContent(content,thumbnail);
        }

        return "redirect:/content";
    }

    @GetMapping("/{id}/delete")
    public String deleteContentById(@PathVariable Integer id) {
        contentService.deleteContentById(id);
        return "redirect:/content";
    }

    @GetMapping("/{id}/detail")
    public String viewContentDetail(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.addAttribute("content", contentService.findContentById(id));
        return "content/detail";
    }

    //
    @GetMapping("/{id}/edit")
    public String viewEditController(@PathVariable("id") Integer id, ModelMap modelMap) {

        Content editContent = contentService.findContentById(id);
        modelMap.addAttribute("categories", categoryService.findCategories());
        modelMap.addAttribute("content", editContent);
        modelMap.addAttribute("isEditAction", true);
        return "content/form";
    }

}
