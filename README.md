# Micro-services -- Projeto para E-commerce


Projeto para E-commerce para reciclagem de conhecimento.

Projeto consiste em diferentes serviços com funcionalidades específicas para facilitar a manutenção sem afetar outras funcionalidades do ecosistema.

Tecnologias utilizadas: 

  - Java 11
  - Springboot
  - Springdata
  - Springcloud
  - Postgres
  - Zipkin
  - Rabbitmq
  - Eureka
  - ApiGateway
  - Docker / Docker-compose
  - Swagger
  - Feign
  - Actuator

Serviços criados:
- Gestão Book
- Gestão Cambio
- Gestão Cadastro de Clientes
- Gestão ConfigServer
- Gestão CEP
- Gestão Usuário
- Gestão Greeting
- Api Gateway
- Autenticador
- Eureka



Acesso ao Rabbitmq: http://localhost:15672/

    - Usuário: guest
    - Senha: guest


Acesso ao Eureka: http://localhost:8761/eureka

Acesso ao Zipkin: http://localhost:9411/zipkin


Comando docker para subir o ZipKin: 
docker run -p 9411:9411 openzipkin/zipkin:2.23.2

Segue arquivo do postman com exemplos de como consumir a api pedidos
 - Postman_collection.json





