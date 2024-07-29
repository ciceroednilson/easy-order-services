package br.com.ciceroednilson.data.model;

import br.com.ciceroednilson.domain.entities.CategoryEntity;

public class CategoryRequestModel {

    public CategoryEntity toEntity() {
        return new CategoryEntity();
    }
}
