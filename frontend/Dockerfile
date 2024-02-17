# Imagem base.
FROM node:18.12.1

# Define o diretório onde estarão os arquivos do app.
WORKDIR /app

# Copia os arquivos do projeto.
COPY . .

# Baixa as libs utilizadas no projeto.
RUN npm install --force

# Exponha a porta na qual o aplicativo será executado (3000 é o padrão que o `server` usa).
EXPOSE 3000

# build app
CMD ["npm", "start"]