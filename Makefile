run:
	docker-compose down
	./gradlew clean build -x test
	docker-compose build
	docker-compose up -d
	docker-compose logs -f ktor-app