package in.vvm.qbar.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import in.vvm.qbar.dto.GenerateCodeRequest;
import in.vvm.qbar.exception.UnsupportedFormatException;
import in.vvm.qbar.service.CodeService;
import in.vvm.qbar.util.ImageUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class CodeServiceImpl implements CodeService {

    @Cacheable(value = "codes", key = "#request.text + '-' + #request.type")
    @Override
    public byte[] generateCodeBytes(GenerateCodeRequest request) throws Exception {
        BufferedImage image = generateCode(request);
        return ImageUtils.toByteArray(image, "PNG");
    }

    private BufferedImage generateCode(GenerateCodeRequest request) throws WriterException {
        int width = request.getWidth();
        int height = request.getHeight();

        BarcodeFormat format;
        try {
            format = BarcodeFormat.valueOf(request.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnsupportedFormatException("Unsupported code type: " + request.getType());
        }
        // MultiFormatWriter can handle all 1D and 2D formats
        BitMatrix bitMatrix = new MultiFormatWriter().encode(request.getText(), format, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
