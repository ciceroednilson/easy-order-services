package br.com.ciceroednilson.data.model;

import java.time.LocalDateTime;

public class CategoryResponseModel extends CategoryRequestModel {

    private LocalDateTime created;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
