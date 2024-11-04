package repository

import (
	"golang-products-consumer/core/ports"
	"golang-products-consumer/infrastructure/entity"
)

type Repository struct{}

func NewRepository() ports.ProductRepositoryPort {
	return &Repository{}
}

func (r *Repository) ReduceStock(product entity.ProductEntity) error {
	return nil
}
