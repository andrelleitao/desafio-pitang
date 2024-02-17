# Desafio Pitang

Para este projeto, foi criado um controle de usuário e carros com Login, no formato de uma API.

 ### Estórias do usuário
  
  1. Como usuário quero realizar o login no sistema para que tenha acesso as funcionalidades.
  2. Como usuário quero criar novos usuários para que estes tenham acesso ao sistema.
  3. Como usuário quero atualizar dados de um usuário específico para que possa atualizar os respectivos dados quando necessário.
  4. Como usuário quero excluir um usuário para que não tenha mais acesso ao sistema.
  5. Como usuário quero visualizar todos os usuário para que possa verificar todos os usuários cadastrados no sistema.
  6. Como usuário quero visualizar um usuário específico a partir do seu ID para que possa visualizar um usuário específico.
  7. Como usuário quero que a aplicação produza um token JWT para que impeça acessos não autorizado a recursos da aplicação.
  8. Como usuário quero que a aplicação verifique se o email do usuário já existe para que seja permitido apenas um email por usuário.
  9. Como usuário quero que a aplicação verifique se o login do usuário já existe para que seja permitido apenas um login por usuário.
  10. Como usuário quero que a aplicação verifique se os valores digitados são válido para que impeça um cadastro quando não estiverem dados obrigatórios preenchidos ou inválidos.
  11. Como usuário quero que a aplicação verifique se a placa de um carro já existe para que seja permitido apenas uma placa por carro
  12. Como usuário quero que sejam exibidas dados do usuário logado para que possa ter informações sobre ele.
  13. Como usuário quero visualizar os carros associados ao usuário logado para que possa visualizar os carros disponíveis para ele.
  14. Como usuário quero cadastrar novos carros para serem associados a usuários.
  15. Como usuário quero alterar dados de um carro para mantê-los sempre atualizados.
  16. Como usuário quero excluir um carro para que não possa ser vinculado a usuários.
  17. Como usuário quero listar todos os carros para que possa verificar todos os carros do sistema. 

### Solução

Foi desenvolvido uma aplicação com as tecnologias Java (Spring Boot) e JavaScript (ReactJS), onde a comunicação entre ambas é feita através de uma API Restful. Essa API é responsável por realizar o controle dos carros e usuários. Também é utilizado um mecanismo de segurança que gera um token JWT que é responsável por impedir qualquer acesso de usuários não autenticados a determinados recursos. O banco utilizado foi o H2.

Foi desenvolvido todas as camadas da aplicação (Models, Service e Controllers) e, por meio dessa aplicação, será possível realizar as operações básicas que se pode fazer em um determinado banco de dados: Criação, Leitura, Atualização e Exclusão (ou CRUD - Create, Read, Update e Delete).

### Executar Frontend

1. Clone o repositório

  - Use o comando: 
   ```sh
   git clone https://github.com/andrelleitao/desafio-pitang.git
   ```
2. Acesse o diretório *frontend* do projeto
3. Instale as dependências
   ```sh
   npm install
   ```
4. Execute o projeto
  ```sh
   npm start
   ```
### Executar Backend

1. Clone o repositório
   
- Use o comando: 
   ```sh
   git clone https://github.com/andrelleitao/desafio-pitang.git
   ```
2. Acesse o diretório *backend* do projeto
3. Faça o build do projeto
   ```sh
   mvn clean install
   ```
4. Execute o projeto
  ```sh
   java -jar .\target\desafio-pitang-0.0.1-SNAPSHOT.jar
   ```
