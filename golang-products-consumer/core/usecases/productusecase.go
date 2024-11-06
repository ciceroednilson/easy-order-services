package usercases

import (
	"fmt"
	"golang-products-consumer/core/domain"
	"golang-products-consumer/core/ports"
	"golang-products-consumer/infrastructure/entity"
	"strconv"
	"strings"
)

type ProductUseCase struct {
	repository ports.ProductRepositoryPort
}

func NewProductUseCase(repository ports.ProductRepositoryPort) ports.ProductUseCasePort {
	return &ProductUseCase{
		repository: repository,
	}
}

func (p *ProductUseCase) ReduceStock(product domain.ProductDomain) error {
	for index, id := range strings.Split(product.Ids, ",") {
		fmt.Printf("index: %d - value: %v", index, id)
		Idnumber, _ := strconv.Atoi(id)
		entity := entity.ProductEntity{Id: Idnumber}
		p.repository.ReduceStock(entity)
	}
	return nil
}
