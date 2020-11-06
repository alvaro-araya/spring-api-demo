# Demo APIs REST y GraphQL

## Configuración de Ambiente

### Servidor de bases de datos

#### Requisitos:

1. Debe instalar [Docker Desktop](https://www.docker.com/products/docker-desktop)
2. Debe instalar **docker-compose** siguiendo las instrucciones correspondientes a su sisrtema operativo [Install Docker Compose](https://docs.docker.com/compose/install/)
3. Dirigirse al directorio `./platform/mariadb`
4. Ejecutar el comando:`$ docker-compose up`
5. Al iniciar automáticamente se inicializará la base de datos **pandemicsdb**.

### Servidor Spring Boot GUID Service

1. Ingresar por medio de terminal o shell a la carpeta de **guidservice** y ejecutar los siguientes comandos:

```
$ mvn clean
$ mvn package
$ java -jar target/guidservice-0.0.1-SNAPSHOT.jar
```

### Servidor Spring Boot Pandemics

1. Para iniciar el servidor Spring Boot debe dirigirse al directorio **pandemics** y ejecutar los siguientes comandos:

```
$ mvn clean
$ mvn spring-boot:run
```

2. Una vez iniciado el servidor dirigirse a la dirección: [http://localhost:8080]()
3. El endpoint de GraphQL sería [http://localhost:8080/graphql]()
4. La interfaz de GraphiQL serían [http://localhost:8080/grapiql]()

@aaocr
