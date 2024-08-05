package br.com.ciceroednilson.domain.entities;

import br.com.ciceroednilson.data.model.CategoryResponseModel;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_category")
public class CategoryEntity extends PanacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "ds_category")
    private String name;

    @Column(name = "dt_created", updatable = false)
    private LocalDateTime created;

    @Column(name = "fl_active")
    private Boolean active;

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CategoryResponseModel toModel() {
        final CategoryResponseModel model = new CategoryResponseModel();
        model.setId(idCategory);
        model.setName(name);
        model.setCreated(created);
        model.setActive(active);
        return model;
    }

    public static List<CategoryResponseModel> toListModel(List<CategoryEntity> entities) {
        return entities.stream().map(CategoryEntity::toModel).toList();
    }
}
