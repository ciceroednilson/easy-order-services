package main

import (
	"fmt"
	"golang-products-consumer/consumer/productconsumer"
	"golang-products-consumer/consumer/request"
	usercases "golang-products-consumer/core/usecases"
	"golang-products-consumer/infrastructure/repository"
	"os"

	"github.com/confluentinc/confluent-kafka-go/v2/kafka"
)

const (
	hostkafka = "127.0.0.1:9092"
	group     = "consumer group - cicero"
	offset    = "smallest"
	topic     = "topic_product"
)

func main() {
	productconsumer := injection()
	kafkaConsumer(productconsumer)
}

func injection() productconsumer.ProductConsumer {
	repository := repository.NewRepository()
	usecase := usercases.NewProductUseCase(repository)
	return productconsumer.NewProductConsumer(usecase)
}

func kafkaConsumer(productconsumer productconsumer.ProductConsumer) {
	consumer, err := kafka.NewConsumer(
		&kafka.ConfigMap{
			"bootstrap.servers": hostkafka,
			"group.id":          group,
			"auto.offset.reset": offset},
	)
	if err != nil {
		panic("Error to consume")
	}
	topics := []string{topic}
	err = consumer.SubscribeTopics(topics, nil)
	if err != nil {
		panic("Error to subscribe the topic")
	}
	run := true
	for run {
		ev := consumer.Poll(100)
		switch e := ev.(type) {
		case *kafka.Message:
			value := string(e.Value)
			request := request.ProductRequest{
				Ids: value,
			}
			productconsumer.ReduceStock(request)
		case kafka.Error:
			fmt.Fprintf(os.Stderr, "%% Error: %v\n", e)
			run = false
		default:
			fmt.Printf("Ignored %v\n", e)
		}
	}
	consumer.Close()

}
