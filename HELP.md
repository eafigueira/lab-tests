# Sobre o projeto

Trata-se de uma API rest simples de um CRUD de person

com os metodos:

GET /person - Lista todos os registros

POST /person - Cria uma nova pessoa

PUT /person/{uuid} - Atualiza um registro de pessoa

DELETE /person/{uuid} - Remove um registro de pessoa

GET /person/{uuid} - Lista um registro de pessoa

### para rodar o projeto basta executar
mvn spring-boot:run

voce pode acessar a api via swagger no endereço http://localhost:8080/swagger-ui/index.html

### para rodar os testes unitários basta executar
mvn test


