# Aplicação Back-End com Java 
<p align="center">
  <img src="https://1000logos.net/wp-content/uploads/2020/09/Java-Logo.png" alt="Logo da Aplicação" width="80" height="50">
</p>
Aplicação Back-End desenvolvida em Java. Abaixo, você encontrará informações técnicas sobre a aplicação, incluindo o que ela é, o que faz e um guia passo a passo para executá-la localmente.

## Sobre a Aplicação
A aplicação é um sistema Back-End desenvolvido em Java que lida com funcionários (Employees). Ela fornece uma API para realizar operações CRUD (Create, Read, Update, Delete) em funcionários. Além disso, a aplicação possui a funcionalidade de validação de CEP (Código de Endereçamento Postal) utilizando uma integração com o serviço ViaCEP.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Hibernate
- Lombok

## Passo a Passo para Execução Local
### Para executar a aplicação localmente, siga as etapas abaixo:

1. Certifique-se de ter o Java JDK instalado em seu sistema.
2. Faça o download do código-fonte da aplicação ou clone o repositório.
3. Abra o projeto em sua IDE favorita.
4. Certifique-se de ter todas as dependências do Maven baixadas e atualizadas.
5. Configure as informações de conexão com o banco de dados no arquivo application.properties ou em um arquivo de configuração equivalente.
6. Execute o arquivo principal da aplicação, geralmente chamado de DemoApplication.java.
7. Aguarde até que a aplicação seja iniciada e esteja em execução.
8. A aplicação estará disponível localmente no endereço `http://localhost:8080`.

## Endpoints da API
A aplicação expõe os seguintes endpoints da API:

1. GET /employee: Retorna todos os funcionários cadastrados.
2. GET /employee/{id}: Retorna um funcionário específico com base no ID fornecido.
3. POST /employee: Cria um novo funcionário com base nos dados fornecidos no corpo da requisição.
4. GET /employee/validate/cep/{zipCode}: Valida um CEP fornecido e retorna informações sobre o endereço, se válido.
5. DELETE /employee/{id}: Deleta um funcionário específico com base no ID fornecido.
#### Certifique-se de utilizar uma ferramenta adequada (como o cURL ou o Postman) para realizar requisições HTTP para os endpoints acima.

## Exceções Personalizadas
A aplicação possui tratamento de exceções personalizadas para lidar com erros específicos. As exceções personalizadas e seus respectivos tratamentos estão definidos na classe RestExceptionHandler.

## Testes
A aplicação inclui testes automatizados para garantir a qualidade do código. Os testes estão localizados no arquivo DocumentUtilTest.java e podem ser executados como parte do processo de construção e execução da aplicação.

## Contato
Se você tiver alguma dúvida ou precisar de mais informações, não hesite em entrar em contato. Você pode me encontrar em:

Email: diego.brandao@outlook.com <br>
LinkedIn: [Diego Brandão](https://www.linkedin.com/in/diego-brand%C3%A3o-dev/)




----------------------------
