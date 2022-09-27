package uz.pdp.appnewsstatus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.Repository.TeachersRepository;
import uz.pdp.appnewsstatus.entity.Attachment;
import uz.pdp.appnewsstatus.entity.Teachers;
import uz.pdp.appnewsstatus.exeption.RestException;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.TeachersDTO;
import uz.pdp.appnewsstatus.service.interfaces.AttachmentService;
import uz.pdp.appnewsstatus.service.interfaces.TeachersService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeachersServiceImpl implements TeachersService {

    private final TeachersRepository teachersRepository;

    private final AttachmentService attachmentService;

    @Override
    public ApiResult<TeachersDTO> getOne(Long id) {
        Teachers teachers = getByIdOrElseThrow(id);
        TeachersDTO teachersDTO = entityToInfoDTO(teachers);
        return ApiResult.successResponse(teachersDTO);
    }

    @Override
    public ApiResult<List<TeachersDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teachers> teachersPage = teachersRepository.findAll(pageable);
        List<Teachers> teachers = teachersPage.getContent();
        List<TeachersDTO> teachersDTOS = teachers
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(teachersDTOS);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResult<TeachersDTO> add(MultipartHttpServletRequest request, TeachersDTO teachersDTO) {

        Attachment attachment = attachmentService.uploadAttachment(request);

        LocalDateTime date = LocalDateTime.now();
        if (!Objects.isNull(teachersDTO.getDate())){
            date = teachersDTO.getDate();
        }
        Teachers teachers = new Teachers(
                teachersDTO.getFullName(),
                teachersDTO.getSubject(),
                date,
                attachment
        );
        teachersRepository.save(teachers);
        return returnApiResult(teachers);
    }

    @Override
    public ApiResult<TeachersDTO> update(MultipartHttpServletRequest request, TeachersDTO teachersDTO, Long id) {
        Attachment attachment = attachmentService.uploadAttachment(request);
        LocalDateTime date = LocalDateTime.now();
        if (!Objects.isNull(teachersDTO.getDate())){
            date = teachersDTO.getDate();
        }
        Teachers teachers = getByIdOrElseThrow(id);
        teachers.setFullName(teachersDTO.getFullName());
        teachers.setSubject(teachers.getSubject());
        teachers.setDate(date);
        teachers.setAttachment(attachment);
        teachersRepository.save(teachers);
        return returnApiResult(teachers);
    }

    @Override
    public String delete(Long id) {
        Teachers teachers = getByIdOrElseThrow(id);
        teachersRepository.delete(teachers);
        return "Successfully deleted";
    }

    @Override
    public Teachers getByIdOrElseThrow(Long id) {
        return teachersRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Teachers")
        );
    }

    private ApiResult<TeachersDTO> returnApiResult(Teachers teachers) {
        TeachersDTO teachersDTO = new TeachersDTO(
                teachers.getId(),
                teachers.getFullName(),
                teachers.getSubject(),
                teachers.getDate(),
                teachers.getAttachment() == null ? null : teachers.getAttachment().getId()
        );
        return new ApiResult<>(teachersDTO, true);
    }



    private TeachersDTO entityToInfoDTO(Teachers teachers) {
        return new TeachersDTO(
                teachers.getId(),
                teachers.getFullName(),
                teachers.getSubject(),
                teachers.getDate(),
                teachers.getAttachment() == null ? null : teachers.getAttachment().getId()
        );
    }
}
