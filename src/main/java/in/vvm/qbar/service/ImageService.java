package in.vvm.qbar.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    byte[] toByteArray(BufferedImage image, String format) throws IOException;
}
