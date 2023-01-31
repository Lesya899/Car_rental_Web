package service;

/**сервис, который будет сохранять в папку C:\work все наши картинки, а в БД будут храниться только ссылки на них
 *для этого необходимо добавить в файл application.properties путь к картинкам - image.base.url=/work
 */

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.PropertiesUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ImageService {

    private static final ImageService IMAGE_INSTANCE = new ImageService();

    private final String basePath = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        var imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(basePath, imagePath);

        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }

    public static ImageService getInstance() {
        return IMAGE_INSTANCE;
    }
}
