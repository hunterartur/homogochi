package com.myhuholi.homogochi.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс предназначенный для работы с файлами
 * @see LocalFileStorage
 * @see MinioFileStorage
 */
public interface FileStorage {
    /**
     * Метод отвечает за сохранение файла
     * В реализации необходимо проверять файл на пустоту
     * @param file Объект файла, содержащий данные в виде массива байтов и название файла, включая расширение
     * @param filePath Путь/Ключ для сохранения файла в хранилище
     */
    void store(MultipartFile file, String filePath);

    /**
     * Метод отвечает за удаление файла
     * @param filePath Путь/Ключ для нахождения файла
     */
    void delete(String filePath);

    /**
     * Метод отвечает за загрузку файла
     * @param filePath Путь/Ключ для нахождения файла
     * @return Одну из реализаций интерфейса {@link Resource}
     */
    Resource load(String filePath);

    /**
     * Выгрузка файла в бинарном виде
     * @param filePath Путь/Ключ для нахождения файла
     * @return Файл в бинарном формате
     */
    byte[] loadFileBytes(String filePath);

    /**
     * Метод отвечает за сохранение файла
     * В реализации необходимо проверять файл на пустоту
     * @param file Объект файла, содержащий данные в виде массива байтов и название файла, включая расширение
     * @param filePath Путь/Ключ для сохранения файла в хранилище
     */
    void store(Resource file, String filePath);

    /**
     * Проверка существования файла
     * @param filePath Путь/Ключ для нахождения файла
     * @return True если файл существует, иначе false
     */
    boolean isFileExists(String filePath);
}
