package uz.pdp.appnewsstatus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.Repository.AttachmentContentRepository;
import uz.pdp.appnewsstatus.Repository.AttachmentRepository;
import uz.pdp.appnewsstatus.controller.AttachmentController;
import uz.pdp.appnewsstatus.entity.Attachment;
import uz.pdp.appnewsstatus.entity.AttachmentContent;
import uz.pdp.appnewsstatus.exeption.RestException;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.AttachmentDTO;
import uz.pdp.appnewsstatus.service.interfaces.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final AttachmentContentRepository attachmentContentRepository;


    @Override
    public Attachment getByIdOrElseThrow(Long id) {
        return attachmentRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Attachment")
        );
    }

    private AttachmentDTO entityToInfoDTO(Attachment attachment){
        String url = AttachmentController.ATTACHMENT_CONTROLLER_PATH
                + AttachmentController.DOWNLOAD_PATH + "/" + attachment.getId();
        return new AttachmentDTO(
                attachment.getId(),
                attachment.getOriginalName(),
                attachment.getSize(),
                attachment.getContentType(),
                url
        );
    }

    @Override
    public ApiResult<AttachmentDTO> getOne(Long id) {
        Attachment attachment = getByIdOrElseThrow(id);
        AttachmentDTO attachmentDTO = entityToInfoDTO(attachment);
        return ApiResult.successResponse(attachmentDTO);
    }

    @Override
    public ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request) {
        try {

            Iterator<String> fileNames = request.getFileNames();

            MultipartFile multipartFile = request.getFile(fileNames.next());

            String contentType = multipartFile.getContentType();//  = image/png

            String originalFilename = multipartFile.getOriginalFilename();

            long size = multipartFile.getSize();

            byte[] bytes = multipartFile.getBytes();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );

            String base64 = Base64.getEncoder().encodeToString(bytes);
            byte[] byteBase64 = base64.getBytes(Charset.forName("UTF-8"));

            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    bytes,
                    byteBase64,
                    attachment
            );

            attachmentContentRepository.save(attachmentContent);

            String url = AttachmentController.ATTACHMENT_CONTROLLER_PATH
                    + AttachmentController.DOWNLOAD_PATH +
                    "/" + attachment.getId();

            AttachmentDTO attachmentDTO = new AttachmentDTO(
                    attachment.getId(),
                    attachment.getOriginalName(),
                    attachment.getSize(),
                    attachment.getContentType(),
                    url
            );

            return ApiResult.successResponse(attachmentDTO);

        } catch (IOException e) {
            e.printStackTrace();
            throw RestException.maker("Fayl yuklashda xatolik", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Attachment uploadAttachment(MultipartHttpServletRequest request){
        try {

            Iterator<String> fileNames = request.getFileNames();

            MultipartFile multipartFile = request.getFile(fileNames.next());

            String contentType = multipartFile.getContentType();//  = image/png

            String originalFilename = multipartFile.getOriginalFilename();

            long size = multipartFile.getSize();

            byte[] bytes = multipartFile.getBytes();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );

            String base64 = Base64.getEncoder().encodeToString(bytes);
            byte[] byteBase64 = base64.getBytes(Charset.forName("UTF-8"));

            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    bytes,
                    byteBase64,
                    attachment
            );

            attachmentContentRepository.save(attachmentContent);

            Optional<Attachment> byId = attachmentRepository.findById(attachment.getId());
            Attachment attachment1 = byId.get();
            return attachment1;

        } catch (IOException e) {
            e.printStackTrace();
            throw RestException.maker("Fayl yuklashda xatolik", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResult<byte[]> getAttachment(Long id) {
        Attachment attachment = getByIdOrElseThrow(id);
        AttachmentContent attachmentContent = attachmentContentRepository.findById(attachment.getId()).orElseThrow();
        byte[] base64 = Base64.getDecoder().decode(attachmentContent.getBase64());
        return ApiResult.successResponse(base64);
    }

    @Override
    public void downloadDb(Long id, boolean inline, HttpServletResponse response) {
        long millis = System.currentTimeMillis();
        try {
            Attachment attachment = getAttachmentByIdOrElseThrow(id);

            AttachmentContent attachmentContent = getAttachmentContentByIdOrElseThrow(attachment.getId());

            String disposition = inline ? "inline" : "attachment";

            response.setHeader("Content-Disposition",
                    disposition + "; filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            response.setHeader("Cache-Control", "max-age=8640000");
            response.setContentLength(Math.toIntExact(attachment.getSize()));

            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());

            System.err.println("Download db => " + (System.currentTimeMillis() - millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Attachment getAttachmentByIdOrElseThrow(Long id) {
        return attachmentRepository.findById(id).orElseThrow(() -> RestException.notFound("Attachment"));
    }

    private AttachmentContent getAttachmentContentByIdOrElseThrow(Long id) {
        return attachmentContentRepository.findByAttachmentId(id).orElseThrow(() -> RestException.notFound("Attachment content"));
    }
}
