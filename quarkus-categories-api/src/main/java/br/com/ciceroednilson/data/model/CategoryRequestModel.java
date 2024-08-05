package br.com.ciceroednilson.data.model;

import br.com.ciceroednilson.domain.entities.CategoryEntity;

public class CategoryRequestModel {

    private Long id;
    private String name;
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryEntity toEntity() {
        final CategoryEntity category = new CategoryEntity();
        category.setIdCategory(id);
        category.setName(name);
        category.setActive(active);
        return category;
    }
}
