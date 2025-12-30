package in.vvm.qbar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.vvm.qbar.service.impl.ImageServiceImpl;

class ImageServiceImplTest {

    private ImageServiceImpl imageService;

    private BufferedImage testImage;

    @BeforeEach
    void setUp() {
        imageService = new ImageServiceImpl();
        testImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    }

    @Test
    void testToByteArray_ValidPngFormat() throws IOException {
        byte[] result = imageService.toByteArray(testImage, "png");
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void testToByteArray_ValidJpgFormat() throws IOException {
        byte[] result = imageService.toByteArray(testImage, "jpg");
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void testToByteArray_UnsupportedFormat() {
        assertThrows(IOException.class, () -> {
            imageService.toByteArray(testImage, "unsupported");
        });
    }

    @Test
    void testToByteArray_NullImage() {
        assertThrows(IllegalArgumentException.class, () -> {
            imageService.toByteArray(null, "png");
        });
    }

    @Test
    void testToByteArray_NullFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            imageService.toByteArray(testImage, null);
        });
    }
}