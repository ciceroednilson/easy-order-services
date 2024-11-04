package ports

import "golang-products-consumer/infrastructure/entity"

type ProductRepositoryPort interface {
	ReduceStock(product entity.ProductEntity) error
}
