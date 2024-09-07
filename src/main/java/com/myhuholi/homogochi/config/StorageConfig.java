package com.myhuholi.homogochi.config;

import com.myhuholi.homogochi.storage.FileStorage;
import com.myhuholi.homogochi.storage.LocalFileStorage;
import com.myhuholi.homogochi.storage.MinioFileStorage;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация файлового хранилища
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@Slf4j
public class StorageConfig {

    /**
     * Регистрирует {@link LocalFileStorage} реализацию {@link FileStorage} если в настройках не указана storage.strategy<br>
     * или если значение storage.strategy является local
     * @param storageProperties Настройки, которые начинаются с префикса storage
     */
    @Bean
    @ConditionalOnProperty(prefix = "storage", name = "strategy", havingValue = "local", matchIfMissing = true)
    public FileStorage localFileStorage(StorageProperties storageProperties) {
        log.info("Using {} file storage strategy", StorageProperties.Strategy.LOCAL);
        return new LocalFileStorage(storageProperties.getPath());
    }

    /**
     * Регистрирует {@link MinioClient}, если в настройках указана storage.strategy=S3
     */
    @Bean
    @ConditionalOnProperty(prefix = "storage", name = "strategy", havingValue = "S3")
    public MinioClient minioClient(StorageProperties storageProperties) {
        log.info("Using {} file storage strategy, initializing {}", StorageProperties.Strategy.S3, MinioClient.class);
        return MinioClient.builder()
                .endpoint(storageProperties.getUrl())
                .credentials(storageProperties.getUser(), storageProperties.getPassword())
                .build();
    }

    /**
     * Регистрирует {@link MinioFileStorage} реализацию {@link FileStorage}<br>
     * если в настройках указана storage.strategy=S3
     * @param storageProperties Настройки, которые начинаются с префикса storage
     * @param minioClient Бин-соединение с minio
     */
    @Bean
    @ConditionalOnProperty(prefix = "storage", name = "strategy", havingValue = "S3")
    @ConditionalOnBean(MinioClient.class)
    public FileStorage minioFileStorage(StorageProperties storageProperties, MinioClient minioClient) {
        log.info("Using {} file storage strategy, initializing {}", StorageProperties.Strategy.S3, MinioFileStorage.class);
        return new MinioFileStorage(minioClient, storageProperties.getPath());
    }
}
