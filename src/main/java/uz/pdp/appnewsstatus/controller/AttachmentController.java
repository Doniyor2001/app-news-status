package uz.pdp.appnewsstatus.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.AttachmentDTO;
import uz.pdp.appnewsstatus.utils.AppConstant;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(AttachmentController.ATTACHMENT_CONTROLLER_PATH)
public interface AttachmentController {
    String ATTACHMENT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/attachment";

    String UPLOAD_PATH = "upload";

    String DOWNLOAD_PATH = "download";

    String VIEW  = "view";

    @GetMapping(VIEW + "/{id}")
    ApiResult<AttachmentDTO> getOne(@PathVariable Long id);

    @GetMapping("get"+"/{id}")
    ApiResult<byte[]> getAttachment(@PathVariable Long id);

    @PostMapping(UPLOAD_PATH)
    ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request);

    @GetMapping(DOWNLOAD_PATH + "/{id}")
    void download(@PathVariable Long id,
                  @RequestParam(defaultValue = "false") boolean inline,
                  HttpServletResponse response);

}
