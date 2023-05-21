package ir.co.sadad.avatarapi.config;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import ir.co.sadad.avatarapi.repositories.BucketRepository;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class MinIoConfig {


    @Value("${file-storage.base_url}")
    private String minIoBaseUrl;
    @Value("${file-storage.access_key}")
    private String accessKey;
    @Value("${file-storage.secret_key}")
    private String secretKey;

    @Value("${file-storage.port}")
    private int port;
    @Value("${file-storage.secure}")
    private boolean secure;


    @Autowired
private BucketRepository bucketRepository;

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

        for (String bucket:bucketRepository.getBuckets()) {
            if (!build.bucketExists(BucketExistsArgs.builder().bucket(bucket).build()))
                build.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucket)
                        .build());
        }
        return build;

    }

}
