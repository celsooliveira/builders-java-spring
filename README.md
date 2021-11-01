# Getting Started

#### Local Dependencies
	
	Java JDK 16
	
	MySqlServer
	
	Docker
	
	Apache Maven
	
	Git
  
#### Project Download

	git clone https://github.com/celsooliveira/builders-java-spring.git
	
	git fetch --all
	
	git checkout -b master  

	
#### Data Base Configuration

	Acesse o arquivo ** application.properties ** e edite as informações conforme exemplo abaixo:
	
	spring.url
	
	spring.username
	
	spring.password
	
#### Compile and Running project with Docker
	
	Acesso o diretório do projeto >>  cd /builders-cliente-api
	
	Execute >> docker-compose up
	
#### Running project with Docker

	Acesso o diretório do projeto >>  cd /builders-cliente-api
	
	Execute build via Maven >> ./mvnw clean package
	
	Efetue a criação da imagem no docker >> docker build -t builders/api-cliente .
		
	Execute a aplicação no docker >> docker run -p 8080:8080 builders/api-cliente	
