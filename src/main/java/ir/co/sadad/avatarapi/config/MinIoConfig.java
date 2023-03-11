package ir.co.sadad.avatarapi.config;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Configuration
public class MinIoConfig {


    @Value("${file-storage.base_url}")
    private String minIoBaseUrl;
    @Value("${file-storage.access_key}")
    private String accessKey;
    @Value("${file-storage.secret_key}")
    private String secretKey;
    @Value("${file-storage.bucket}")
    private String bucket;
    @Value("${file-storage.port}")
    private int port;
    @Value("${file-storage.secure}")
    private boolean secure;


    @Bean
    public MinioClient minioClient() throws IOException,
            InvalidKeyException,
            InvalidResponseException,
            InsufficientDataException,
            NoSuchAlgorithmException,
            InternalException,
            ErrorResponseException,
            ServerException,
            XmlParserException {


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .build();

        MinioClient build = MinioClient.builder()
                .credentials(accessKey, secretKey)
                .endpoint(minIoBaseUrl, port, secure)
                .build();


        boolean isExist = build
                .bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (isExist) {
            System.out.println("Bucket already exists.");
        } else {
            build.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucket)
                    .build());
        }

        return build;

    }

}
