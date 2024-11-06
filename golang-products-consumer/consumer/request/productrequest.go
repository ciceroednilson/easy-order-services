package request

import "golang-products-consumer/core/domain"

type ProductRequest struct {
	Ids string
}

func (p *ProductRequest) ToDomain() domain.ProductDomain {
	return domain.ProductDomain{
		Ids: p.Ids,
	}
}
