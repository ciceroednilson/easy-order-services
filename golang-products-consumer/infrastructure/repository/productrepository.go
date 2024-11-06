package repository

import (
	"errors"
	"fmt"
	"golang-products-consumer/core/ports"
	"golang-products-consumer/infrastructure/entity"
	"log"
	"net/http"
)

type Repository struct{}

func NewRepository() ports.ProductRepositoryPort {
	return &Repository{}
}

func (r *Repository) ReduceStock(product entity.ProductEntity) error {
	// Define the URL of the external API endpoint
	url := fmt.Sprintf("http://127.0.0.1:9000/product/%v/reduce/stock", product.Id)
	req, err := http.NewRequest(http.MethodPut, url, nil)
	if err != nil {
		log.Fatalf("Error creating request: %v", err)
	}
	// Set the Content-Type header to application/json
	req.Header.Set("Content-Type", "application/json")
	// Create a new HTTP client and send the request
	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		log.Fatalf("Error sending request: %v", err)
	}
	defer resp.Body.Close()
	// Check if the request was successful (status code 200-299)
	if resp.StatusCode >= 200 && resp.StatusCode < 300 {
		log.Println("Request successful!")
		return nil
	}
	msg := fmt.Sprintf("Request failed with status code %d:", resp.StatusCode)
	fmt.Println(msg)
	return errors.New(msg)
}
