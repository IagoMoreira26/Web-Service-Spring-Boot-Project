# Web-Service-Spring-Boot-Project

Projeto feito com as seguintes tecnologias:

**Backend:** Java e Springboot

**SGBD:** H2

**ORM:** Hibernate

[![My Skills](https://skillicons.dev/icons?i=java,spring,hibernate)](https://skillicons.dev)

## Descrição Geral do Sistema

O sistema tem como objetivo gerenciar **usuários, produtos, categorias, pedidos e pagamentos** em uma plataforma de comércio eletrônico. Ele permite que clientes façam pedidos de produtos, acompanhem o status dos pedidos e efetuem pagamentos.

## Documentação da API

Execute o projeto Spring e entre no link: http://localhost:8080/swagger-ui/index.html#/

## Modelo de Domínio

Este modelo representa a estrutura do sistema, semelhante a de um **comércio eletrônico (e-commerce)**, com entidades relacionadas a usuários, produtos, pedidos, pagamentos e categorias.

```mermaid
classDiagram
    class User {
        +Integer id
        +String name
        +String email
        +String phone
        +String password
    }

    class Order {
        +Integer id
        +Date moment
        +OrderStatus orderStatus
        +double total()
    }

    class OrderItem {
        +Integer quantity
        +Double price
        +double subTotal()
    }

    class Product {
        +Integer id
        +String name
        +String description
        +Double price
        +String imgUrl
    }

    class Category {
        +Integer id
        +String name
    }

    class Payment {
        +Integer id
        +Date moment
    }

    class OrderStatus {
        <<enumeration>>
        WAITING_PAYMENT
        PAID
        SHIPPED
        DELIVERED
        CANCELED
    }

    %% RELACIONAMENTOS
    User "1" --> "*" Order : orders
    Order "1" --> "0..1" Payment : payment
    Order "1" --> "*" OrderItem : items
    OrderItem "*" --> "1" Order : order
    OrderItem "*" --> "1" Product : product
    Product "*" --> "*" Category : categories
````

### 👤 Usuário (User)

Cada usuário possui informações pessoais como nome, e-mail, telefone e senha. Um usuário pode realizar **vários pedidos (orders)**, mas cada pedido pertence a **um único usuário**.

### 🧾 Pedido (Order)

O pedido representa uma **compra feita pelo usuário**. Ele armazena:

- A data (`moment`),
- O status do pedido (`orderStatus`, baseado no enum `OrderStatus`),
- O valor total (`total()`).

Cada pedido está vinculado a um usuário e pode conter **vários itens (OrderItem)**. E também pode estar associado a **um pagamento (Payment)**, que é opcional (0..1).

### 📦 Item do Pedido (OrderItem)

Representa a **relação entre o pedido e o produto**. Guarda a quantidade e o preço do produto no momento da compra, e calcula o subtotal. Um pedido pode ter vários itens, e um produto pode aparecer em vários pedidos.

### 🧰 Produto (Product)

Contém informações como nome, descrição, preço e URL da imagem. Um produto pode pertencer a **várias categorias** (relação N:N) e aparecer em vários pedidos (por meio de `OrderItem`).

### 🗂️ Categoria (Category)

Serve para **organizar os produtos** em grupos (ex: eletrônicos, vestuário, etc.). Um produto pode pertencer a uma ou mais categorias.

### 💳 Pagamento (Payment)

Cada pagamento está associado a **um único pedido**. Contém um identificador e o momento em que foi realizado.

### 🔁 OrderStatus (Enum)

Define o **estado atual do pedido**, podendo ser:

- `WAITING_PAYMENT` – aguardando pagamento
- `PAID` – pago
- `SHIPPED` – enviado
- `DELIVERED` – entregue
- `CANCELED` – cancelado

### Resumo

O sistema modela todas as etapas de um processo de compra:

1. O **usuário** faz um **pedido** com vários **produtos**.
2. Cada produto pertence a uma ou mais **categorias**.
3. O pedido contém **itens**, cada um com quantidade e preço.
4. O pedido pode ter um **pagamento** e um **status** de andamento.

## Arquitetura do Backend

A aplicação segue uma arquitetura em camadas, separando responsabilidades para garantir **organização, manutenção e escalabilidade** do código.

### Application

Camada principal que representa o ponto de entrada da aplicação. É responsável por inicializar o sistema, configurar dependências e gerenciar o ciclo de vida da aplicação.

### Resource Layer (Rest Controllers)

Também chamada de **camada de controle**, é a responsável por **receber as requisições HTTP** e **retornar as respostas** adequadas. Ela não contém lógica de negócio, apenas faz a comunicação entre o cliente (frontend) e as camadas internas do sistema.  

### Service Layer

A camada de serviço contém a **lógica de negócio da aplicação**. Aqui ficam as regras que definem o funcionamento do sistema, como validações, cálculos ou fluxos de execução antes de acessar o banco de dados. Ela atua como intermediária entre o **controller** e o **repositório**.

### Data Access Layer (Data Repositories)

Camada responsável pelo **acesso direto ao banco de dados**. Aqui são implementadas as operações de persistência, consulta, atualização e exclusão de dados (CRUD). Normalmente faz uso de frameworks ORM (Hibernate) para mapear as entidades e gerenciar a comunicação com o banco.

### Entities

As **entidades** representam os **objetos de domínio** do sistema, geralmente correspondem às tabelas do banco de dados. São modelos que definem os atributos e relacionamentos dos dados que o sistema manipula.
