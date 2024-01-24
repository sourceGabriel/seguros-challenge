<div align="center">
<h1><a>Desafio Produtos de Seguros 🚀</a></h1>
</div>

## Sobre

O projeto "Desafio de Seguros" é uma aplicação Java que simula um sistema de seguros. Ele oferece funcionalidades básicas para a gestão de produtos de seguro, como a criação, atualização e listagem de produtos. Cada produto possui informações como nome, categoria, preço base e preço tarifado.

Os impostos devem ser aplicados da seguinte forma:


| **Categoria** | **Imposto sobre Operação Finaneira (IOF)** | **Programa de Integração Social (PIS)** | **Contribuição para o Financiamento da Seguridade Social (COFINS)** |
|---------------|--------------------------------------------|-----------------------------------------|----------------------------------------------------------------|
| VIDA          | 1%                                         | 2.2%                                      | Não há                                                          |
| AUTO          | 5.5%                                       | 4%                                      | 1%                                                             |
| VIAGEM        | 2%                                         | 4%                                      | 1%                                                             |
| RESIDENCIAL   | 4%                                         | Não há                                | 3%                                                             |
| PATRIMONIAL   | 5%                                         | 3%                                      | Não há                                                           |

## Requisitos

- Java 8 ou superior
- Maven
- Docker (opcional, para execução em contêiner)

### Configuração do Ambiente

Certifique-se de ter o [Java](https://www.oracle.com/java/technologies/javase-downloads.html) instalado em sua máquina.

### Configuração do Banco de Dados

O aplicativo utiliza um banco de dados para armazenar informações sobre produto de seguro. Certifique-se de configurar um banco de dados MySQL e ajuste as configurações no arquivo `application.properties`.

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/seguros
spring.datasource.username=root
spring.datasource.password=root
```

## Como Executar

### Localmente

1. Clone o repositório:

    ```bash
    git clone https://github.com/sourceGabriel/seguros-challenge.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd challenge
    ```

3. Execute a aplicação:

    ```bash
    mvn spring-boot:run
    ```

A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

### Utilizando Docker para banco de dados

1. Certifique-se de ter o Docker instalado.
```
docker-compose up
```
E caso queira se conectar ao mysql com o comando
```
mysql -h localhost -P 3306 --protocol=tcp -u root
```

Execute o aplicativo Spring Boot utilizando o seguinte comando:

```bash
./mvnw spring-boot:run
```
O aplicativo estará disponível em http://localhost:8080.

### API Endpoints:  
Listar Produtos endpoint :
```
GET /produto
Descrição:
Retorna uma lista de todos os produto de seguro disponíveis.
```
Criar Produto endpoint :
```
POST /produto
Descrição:
Cria um novo produto de seguro. O preço do produto será calculado utilizando o caso de uso CalculaPrecoTarifadoUseCase.
```
Criar Produto endpoint :
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

## Cobertura de Testes
### [Para visualizar a cobertura de testes clique aqui](https://sourcegabriel.github.io/seguros-challenge/).
