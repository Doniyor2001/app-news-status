package uz.pdp.appnewsstatus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.AttachmentDTO;
import uz.pdp.appnewsstatus.service.interfaces.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController{

    private final AttachmentService attachmentService;

    @Override
    public ApiResult<AttachmentDTO> getOne(Long id) {
        return attachmentService.getOne(id);
    }

    @Override
    public ApiResult<byte[]> getAttachment(Long id) {
        return attachmentService.getAttachment(id);
    }

    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadDb(request);
    }

    @Override
    public void download(Long id, boolean inline, HttpServletResponse response) {
        attachmentService.downloadDb(id,inline,response);
    }
}
