# Especifica la imagen base de Java 8
FROM openjdk:8-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8080
#RUN javac demo.java
# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]
