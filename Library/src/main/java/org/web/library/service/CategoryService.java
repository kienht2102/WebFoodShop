package org.web.library.service;

import org.web.library.dto.CategoryDto;
import org.web.library.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);

    Category update(Category category);

    List<Category> findAllByActivatedTrue();

    List<Category> findALl();

    Optional<Category> findById(Long id);

    void deleteById(Long id);

    void enableById(Long id);

    List<CategoryDto> getCategoriesAndSize();
}
