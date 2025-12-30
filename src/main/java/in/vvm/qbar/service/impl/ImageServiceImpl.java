package in.vvm.qbar.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import org.springframework.stereotype.Service;

import in.vvm.qbar.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public byte[] toByteArray(BufferedImage image, String format) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            if (!ImageIO.write(image, format, baos)) {
                throw new IOException("Unsupported image format: " + format);
            }
            return baos.toByteArray();
        }
    }
    
}
