package uz.pdp.appnewsstatus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.TeachersDTO;
import uz.pdp.appnewsstatus.service.interfaces.TeachersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeachersControllerImpl implements TeachersController{

    private final TeachersService teachersService;

    @Override
    public ApiResult<List<TeachersDTO>> getAll(int page, int size) {
        return teachersService.getAll(page, size);
    }

    @Override
    public ApiResult<TeachersDTO> getOne(Long id) {
        return teachersService.getOne(id);
    }

    @Override
    public ApiResult<TeachersDTO> add(MultipartHttpServletRequest request, TeachersDTO teachersDTO) {
        return teachersService.add(request, teachersDTO);
    }

    @Override
    public ApiResult<TeachersDTO> update(MultipartHttpServletRequest request, TeachersDTO teachersDTO, Long id) {
        return teachersService.update(request, teachersDTO, id);
    }

    @Override
    public String delete(Long id) {
        return teachersService.delete(id);
    }
}
