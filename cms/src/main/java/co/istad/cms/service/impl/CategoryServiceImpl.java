package co.istad.cms.service.impl;

import co.istad.cms.model.Category;
import co.istad.cms.repository.CategoryRepository;
import co.istad.cms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;



    @Override
    public List<Category> findCategories() {
        return categoryRepository.select();
    }


}
