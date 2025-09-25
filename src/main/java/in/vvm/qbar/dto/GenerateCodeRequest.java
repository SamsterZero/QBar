package in.vvm.qbar.dto;

import lombok.Data;

@Data
public class GenerateCodeRequest {
    private String text;
    private String type = "QR_CODE"; // default
    private int width = 300;
    private int height = 300;
}