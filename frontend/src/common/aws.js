import AWS from 'aws-sdk';

const S3_BUCKET = process.env.REACT_APP_AWS_S3_BUCKET;
const REGION = process.env.REACT_APP_AWS_REGION;
const ACCESS_KEY = process.env.REACT_APP_AWS_ACCESS_KEY;
const SECRET_ACCESS_KEY = process.env.REACT_APP_AWS_SECRET_ACCESS_KEY;

// Realiza a configuração da instância do AWS.
AWS.config.update({
    accessKeyId: ACCESS_KEY,
    secretAccessKey: SECRET_ACCESS_KEY
});

// Define o bucket que será utilizado.
export const s3 = new AWS.S3({
    params: { Bucket: S3_BUCKET},
    region: REGION,
});

export const s3Params = {
    ACL: "public-read",
    Bucket: S3_BUCKET,
    Body: null, // Conterá o arquivo que será enviado.
    Key: null, // Conterá o nome do arquivo.
}