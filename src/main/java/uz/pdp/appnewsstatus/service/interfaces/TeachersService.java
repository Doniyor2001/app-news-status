package uz.pdp.appnewsstatus.service.interfaces;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.entity.Teachers;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.TeachersDTO;

import java.util.List;

public interface TeachersService {

    ApiResult<TeachersDTO> getOne(Long id);

    ApiResult<List<TeachersDTO>> getAll(int page, int size);

    ApiResult<TeachersDTO> add(MultipartHttpServletRequest request, TeachersDTO teachersDTO);

    ApiResult<TeachersDTO> update(MultipartHttpServletRequest request, TeachersDTO teachersDTO, Long id);

    String delete(Long id);

    Teachers getByIdOrElseThrow(Long id);

}
