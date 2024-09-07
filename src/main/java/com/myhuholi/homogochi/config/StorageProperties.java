package com.myhuholi.homogochi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Настройки файлового хранилища, начинаются с префикса storage
 */
@ConfigurationProperties(prefix = "storage")
@Getter
@Setter
public class StorageProperties {
    /**
     * Тип файлового хранилища, по умолчанию локальное хранилище
     * @see Strategy
     */
    private Strategy strategy = Strategy.LOCAL;
    /**
     * Корневой каталог/путь/bucket файлового хранилища, по умолчанию files
     */
    private String path = "files";
    /**
     * URL для подключения к файловому хранилищу при {@link Strategy стратегии} S3
     */
    private String url;
    /**
     * Имя пользователя для подключения при {@link Strategy стратегии} S3
     */
    private String user;
    /**
     * Пароль для подключения при {@link Strategy стратегии} S3
     */
    private String password;

    public enum Strategy {
        LOCAL, S3
    }
}
