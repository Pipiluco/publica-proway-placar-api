# Sobre o projeto

#### O projeto foi desenvolvido em Java com base no framework Spring Boot e MySQL.
		
#### Esta projeto trabalha com o propósito de cadastrar em uma base de dados informações sobre uma determinada temporada de jogos que 
#### contenha jogos e os jogos contenha jogadores com suas pontuações.

	
## Requisítos para execução do projeto
#### Observação: as configurações para execução do projeto podem ser alteradas no arquivo _application.properties_
~~~
	# Servidor WEB
	server.port=${port:80}
	
	# Bando de dados
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	spring.datasource.url=jdbc:mysql://localhost:3307/publica_proway_placar?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC
	spring.datasource.username=root
	spring.datasource.password=root
	
	spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
	spring.jpa.hibernate.ddl-auto=update
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.format_sql=true
~~~
	
- Banco de dados MySQL 8 com usuário _root_ e senha _root_
- Java JDK 8
- IDE Spring Tool Suite ou Eclipse
