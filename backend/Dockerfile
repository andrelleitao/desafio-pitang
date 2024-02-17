FROM amazoncorretto:20-alpine-jdk

# Instala fonts para serem utilizadas pelo Jasper.
# Isso é obrigatório para poder utilizar o Jasper.
RUN apk add --no-cache msttcorefonts-installer fontconfig
RUN update-ms-fonts

# Define build como o diretório de trabalho. Quaisquer comandos subseqüentes são executados neste diretório.
WORKDIR /app

# Realiza a cópia do pom.xml, src e dos arquivos do Maven Wrapper.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Ocorre, tenha a linha “RUN dos2unix mvnw” antes de “ RUN ./mvnw dependency:go-offline”, 
# pois isso corrigirá quaisquer problemas de terminações de linha no script mvnw.
RUN dos2unix mvnw
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

# É necessário que tenha o . em "./mvnw" para que o Docker entenda que é o arquivo que acabou de ser copiado.
CMD ["./mvnw", "spring-boot:run"]
