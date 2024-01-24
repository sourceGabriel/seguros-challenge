<div align="center">
<h1><a>Desafio Produtos de Seguros 🚀</a></h1>
</div>

## Sobre

O projeto "Desafio de Seguros" é uma aplicação Java que simula um sistema de seguros. Ele oferece funcionalidades básicas para a gestão de produtos de seguro, como a criação, atualização e listagem de produtos. Cada produto possui informações como nome, categoria, preço base e preço tarifado.


## Configuração do Ambiente

Certifique-se de ter o [Java](https://www.oracle.com/java/technologies/javase-downloads.html) instalado em sua máquina.

## Configuração do Banco de Dados

O aplicativo utiliza um banco de dados para armazenar informações sobre produto de seguro. Certifique-se de configurar um banco de dados MySQL e ajuste as configurações no arquivo `application.properties`.

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/seguros
spring.datasource.username=root
spring.datasource.password=root
```
Execute o aplicativo Spring Boot utilizando o seguinte comando:

```bash
./mvnw spring-boot:run
```
O aplicativo estará disponível em http://localhost:8080.

API Endpoints:  
Listar Produtos
```
GET /produto
Descrição:
Retorna uma lista de todos os produto de seguro disponíveis.
```
Criar Produto
Endpoint:


```
POST /produto
Descrição:
Cria um novo produto de seguro. O preço do produto será calculado utilizando o caso de uso CalculaPrecoTarifadoUseCase.
```
Criar Produto
Endpoint:
```
PUT /produto/${id}
Descrição:
Atualiza produto de seguro. O preço do produto será calculado utilizando o caso de uso CalculaPrecoTarifadoUseCase.
```

**Exemplo de Requisição**

```json
{
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 100.00
}
```

**Exemplo de Resposta**

```json
{
  "id": "8cfb5eb2-fd93-4322-bb74-c82f27c95a47",
  "nome": "Seguro de Vida Individual",
  "categoria": "VIDA",
  "preco_base": 100.00,
  "preco_tarifado": 106.00
}
```
Testes de Integração
Este projeto inclui testes de integração usando o framework JUnit e o Spring Boot Test. 
Execute os testes de integração com o seguinte comando:

```bash
./mvnw test
```