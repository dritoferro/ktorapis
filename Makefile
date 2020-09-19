run:
	docker-compose down
	./gradlew clean build -x test
	docker-compose build
	docker-compose up -d
	echo "A aplicação está iniciando"
	echo "Aguardando 20 segundos para configurar o Keycloak..."
	sleep 20
	docker exec -it ktorrestapis_keycloak_1 /keycloak-setup/keycloak-init.sh
	docker-compose logs -f ktor-app