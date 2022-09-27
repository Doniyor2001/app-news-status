package uz.pdp.appnewsstatus.service.interfaces;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.entity.Attachment;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.AttachmentDTO;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {

    Attachment getByIdOrElseThrow(Long id);

    ApiResult<AttachmentDTO> getOne(Long id);

    Attachment uploadAttachment(MultipartHttpServletRequest request);

    ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request);

    ApiResult<byte[]> getAttachment(Long id);

    void downloadDb(Long id, boolean inline, HttpServletResponse response);

}
