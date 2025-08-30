package in.vvm.qbar.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import in.vvm.qbar.dto.GenerateCodeRequest;
import in.vvm.qbar.exception.UnsupportedFormatException;
import in.vvm.qbar.service.CodeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class CodeServiceImpl implements CodeService {

    @Cacheable(value = "codes", key = "#request.text + '-' + #request.type")
    @Override
    public byte[] generateCodeBytes(GenerateCodeRequest request) throws Exception {
        BufferedImage image = generateCode(request);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        return baos.toByteArray();
    }

    private BufferedImage generateCode(GenerateCodeRequest request) throws WriterException {
        int width = request.getWidth();
        int height = request.getHeight();

        BarcodeFormat format = switch (request.getType().toUpperCase()) {
            case "QR" -> BarcodeFormat.QR_CODE;
            case "CODE_128" -> BarcodeFormat.CODE_128;
            default -> throw new UnsupportedFormatException("Unsupported code type: " + request.getType());
        };

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(request.getText(), format, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
