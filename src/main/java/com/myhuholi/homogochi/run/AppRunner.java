package com.myhuholi.homogochi.run;

import com.myhuholi.homogochi.config.StorageProperties;
import com.myhuholi.homogochi.domain.HomoPicture;
import com.myhuholi.homogochi.service.HomoPictureService;
import com.myhuholi.homogochi.storage.FileStorage;
import com.myhuholi.homogochi.storage.LocalFileStorage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {
    private final FileStorage fileStorage;
    private final StorageProperties storageProperties;
    private final HomoPictureService pictureService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Initializing file storage");
        LocalFileStorage localFileStorage = new LocalFileStorage(storageProperties.getPath());

        List<HomoPicture> defaultPictures = pictureService.getDefaultPictures();
        for (HomoPicture defaultPicture : defaultPictures) {
            String pictureFileName = defaultPicture.getPictureFileName();
            log.info(String.format("Checking is %s file exists", pictureFileName));
            if (fileStorage.isFileExists(pictureFileName)) {
                log.info(String.format("File %s exists", pictureFileName));
                continue;
            }

            log.info(String.format("File %s not exists, loading file from local fileStorage", pictureFileName));
            Resource picture = localFileStorage.load(pictureFileName);
            log.debug(String.format("Loaded file resource %s", picture));
            log.info(String.format("File %s loaded, uploading file to fileStorage", pictureFileName));
            fileStorage.store(picture, pictureFileName);
            log.info(String.format("Uploaded file %s to fileStorage", pictureFileName));
        }

    }
}
