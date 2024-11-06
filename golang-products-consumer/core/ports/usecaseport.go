package ports

import (
	"golang-products-consumer/core/domain"
)

type ProductUseCasePort interface {
	ReduceStock(product domain.ProductDomain) error
}
