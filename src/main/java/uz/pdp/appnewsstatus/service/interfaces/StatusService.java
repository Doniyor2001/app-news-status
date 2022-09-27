package uz.pdp.appnewsstatus.service.interfaces;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.entity.Status;
import uz.pdp.appnewsstatus.entity.Teachers;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.StatusDTO;
import uz.pdp.appnewsstatus.payload.TeachersDTO;

import java.util.List;

public interface StatusService {

    ApiResult<StatusDTO> getOne(Long id);

    ApiResult<List<StatusDTO>> getAll(int page, int size);

    ApiResult<StatusDTO> add(StatusDTO statusDTO);

    ApiResult<StatusDTO> update(StatusDTO statusDTO, Long id);

    String delete(Long id);

    Status getByIdOrElseThrow(Long id);

}
