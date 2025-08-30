package in.vvm.qbar.controller;

import in.vvm.qbar.dto.GenerateCodeRequest;
import in.vvm.qbar.service.CodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/codes")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateCode(@RequestBody GenerateCodeRequest request) throws Exception {
        byte[] imageBytes = codeService.generateCodeBytes(request);
        return ResponseEntity.ok(imageBytes);
    }

}
