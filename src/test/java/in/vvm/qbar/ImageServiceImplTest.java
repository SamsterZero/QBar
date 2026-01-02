package in.vvm.qbar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import in.vvm.qbar.service.impl.ImageServiceImpl;

@DisplayName("ImageServiceImpl Test")
class ImageServiceImplTest {

    private ImageServiceImpl imageService;

    private BufferedImage testImage;

    @BeforeEach
    void setUp() {
        imageService = new ImageServiceImpl();
        testImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    }

    @Test
    @DisplayName("Valid PNG Format")
    void testToByteArray_ValidPngFormat() throws IOException {
        byte[] result = imageService.toByteArray(testImage, "png");
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    @DisplayName("Valid JPG Format")
    void testToByteArray_ValidJpgFormat() throws IOException {
        byte[] result = imageService.toByteArray(testImage, "jpg");
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    @DisplayName("Unsupported Format")
    void testToByteArray_UnsupportedFormat() {
        assertThrows(IOException.class, () -> {
            imageService.toByteArray(testImage, "unsupported");
        });
    }

    @Test
    @DisplayName("Null Image")
    void testToByteArray_NullImage() {
        assertThrows(IllegalArgumentException.class, () -> {
            imageService.toByteArray(null, "png");
        });
    }

    @Test
    @DisplayName("Null Format")
    void testToByteArray_NullFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            imageService.toByteArray(testImage, null);
        });
    }
}