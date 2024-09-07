package com.myhuholi.homogochi.storage;

import com.myhuholi.homogochi.exception.StorageException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Рализация интерфейса {@link FileStorage}, которая хранит файлы локально на той же машине, где и запущено приложение
 */
public class LocalFileStorage implements FileStorage {
    /**
     * Корневой путь/каталог хранилища
     */
    private final Path rootLocation;

    /**
     * Создает хранилище с указанным корневым каталогом
     * @param storagePath Корневой путь, важно знать, что путь, который начинается с символа '/',
     *                    отсчитывается с корневого каталога системы, поэтому держите это в голове
     */
    public LocalFileStorage(String storagePath) {
        this.rootLocation = Paths.get(storagePath).toAbsolutePath();
    }

    /**
     * Производит инициализацию хранилища
     */
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    /**
     * Сохраняет файл локально на машине по указанному пути
     * @param file Объект файла, содержащий данные в виде массива байтов и название файла, включая расширение
     * @param filePath Путь/Ключ для сохранения файла в хранилище
     * @throws StorageException если файл пустой, <br>
     * или если путь указан неверно и выходит за границы корневого каталога хранилища, <br>
     * или если произошла иная ошибка чтения/записи
     */
    @Override
    public void store(MultipartFile file, String filePath) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty");
            }

            Path destinationPath = this.rootLocation.resolve(Paths.get(filePath)).normalize().toAbsolutePath();

            if (!destinationPath.startsWith(this.rootLocation)) {
                throw new StorageException("Can't store file outside storage");
            }

            Files.createDirectories(destinationPath.getParent());
            Files.write(destinationPath, file.getBytes());
        } catch (IOException e) {
            throw new StorageException("Failed to store file: %s".formatted(file.getOriginalFilename()), e);
        }
    }

    /**
     * Удаляет файл по указанному пути
     * @param filePath Путь/Ключ для нахождения файла
     * @throws StorageException если указанный путь выходит за пределы корневого каталога хранилища<br>
     * или если файл не найден<br>
     * или если произошла иная ошибка чтения/записи
     */
    @Override
    public void delete(String filePath) {
        try {
            Path destinationPath = this.rootLocation.resolve(Paths.get(filePath)).normalize().toAbsolutePath();

            if (!destinationPath.startsWith(this.rootLocation)) {
                throw new StorageException("Can't delete file outside storage");
            }

            Files.delete(destinationPath);
        } catch (NoSuchFileException e) {
            throw new StorageException("Failed to delete file, file not found: %s".formatted(filePath), e);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file: %s".formatted(filePath), e);
        }
    }

    /**
     * Загружает файл по указанному пути
     * @param filePath Путь для нахождения файла
     * @return {@link UrlResource} реализацию интерфейса {@link Resource}
     * @throws StorageException если указанный путь выходит за границы корневого каталога хранилища<br>
     * или если файл не существует<br>
     * или если файл невозможно прочитать<br>
     * или если произошла иная ошибка чтения/записи
     */
    @Override
    public Resource load(String filePath) {
        try {
            Path destinationPath = this.rootLocation.resolve(Paths.get(filePath)).normalize().toAbsolutePath();

            if (!destinationPath.startsWith(this.rootLocation)) {
                throw new StorageException("Can't load file outside storage");
            }

            Resource resource = new UrlResource(destinationPath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }

            throw new StorageException("File not found: %s".formatted(filePath), StorageError.NOT_FOUND_ERROR);
        } catch (IOException e) {
            throw new StorageException("Failed to load file: %s".formatted(filePath), e);
        }
    }
}
