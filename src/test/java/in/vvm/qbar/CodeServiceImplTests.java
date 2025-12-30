package in.vvm.qbar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import in.vvm.qbar.dto.GenerateCodeRequest;
import in.vvm.qbar.exception.UnsupportedFormatException;
import in.vvm.qbar.service.impl.CodeServiceImpl;
import in.vvm.qbar.service.impl.ImageServiceImpl;

@ExtendWith(MockitoExtension.class)
class CodeServiceImplTests {

    @Mock
    private ImageServiceImpl imageService;

    @InjectMocks
    private CodeServiceImpl codeService;

    private GenerateCodeRequest request;

    @BeforeEach
    void setUp() {
        request = new GenerateCodeRequest();
        request.setText("https://example.com");
        request.setType("QR_CODE");
        request.setWidth(200);
        request.setHeight(200);
    }

    @Test
    void testGenerateCodeBytes_ValidQRCode() throws Exception {
        when(imageService.toByteArray(any(BufferedImage.class), eq("PNG")))
            .thenReturn(new byte[]{1, 2, 3, 4});
        
        byte[] result = codeService.generateCodeBytes(request);
        
        assertNotNull(result);
        assertTrue(result.length > 0);
        verify(imageService).toByteArray(any(BufferedImage.class), eq("PNG"));
    }

    @Test
    void testGenerateCodeBytes_ValidBarcodeCode128() throws Exception {
        request.setType("CODE_128");
        request.setText("123456789");
        
        when(imageService.toByteArray(any(BufferedImage.class), eq("PNG")))
            .thenReturn(new byte[]{1, 2, 3, 4});
        
        byte[] result = codeService.generateCodeBytes(request);
        
        assertNotNull(result);
        assertTrue(result.length > 0);
        verify(imageService).toByteArray(any(BufferedImage.class), eq("PNG"));
    }

    @Test
    void testGenerateCodeBytes_UnsupportedFormat() throws IOException {
        request.setType("INVALID_TYPE");
        
        assertThrows(UnsupportedFormatException.class, () -> {
            codeService.generateCodeBytes(request);
        });
        
        verify(imageService, times(0)).toByteArray(any(BufferedImage.class), anyString());
    }

    @Test
    void testGenerateCodeBytes_NullText() throws IOException {
        request.setText(null);
        
        assertThrows(Exception.class, () -> {
            codeService.generateCodeBytes(request);
        });
        
        verify(imageService, times(0)).toByteArray(any(BufferedImage.class), anyString());
    }

    @Test
    void testGenerateCodeBytes_ImageServiceCalled() throws Exception {
        when(imageService.toByteArray(any(BufferedImage.class), eq("PNG")))
            .thenReturn(new byte[]{1, 2, 3, 4});
        
        codeService.generateCodeBytes(request);
        
        verify(imageService, times(1)).toByteArray(any(BufferedImage.class), eq("PNG"));
    }

}