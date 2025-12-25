package in.vvm.qbar.service;

import in.vvm.qbar.dto.GenerateCodeRequest;
import org.springframework.stereotype.Service;

@Service
public interface CodeService
{
    byte[] generateCodeBytes(GenerateCodeRequest request) throws Exception;
}
