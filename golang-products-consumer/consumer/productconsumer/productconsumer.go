package productconsumer

import (
	"golang-products-consumer/consumer/request"
	"golang-products-consumer/core/ports"
	"log"
)

type ProductConsumer struct {
	productUseCasePort ports.ProductUseCasePort
}

func NewProductConsumer(productUseCasePort ports.ProductUseCasePort) ProductConsumer {
	return ProductConsumer{
		productUseCasePort: productUseCasePort,
	}
}

func (p *ProductConsumer) ReduceStock(request request.ProductRequest) {
	log.Println("Starting the reduce stock.")
	domain := request.ToDomain()
	err := p.productUseCasePort.ReduceStock(domain)
	if err != nil {
		log.Printf("Error to execute reduce stock: %v", err)
		panic(err)
	}
}
