package uz.pdp.appnewsstatus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.StatusDTO;
import uz.pdp.appnewsstatus.service.interfaces.StatusService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatusControllerImpl implements StatusController{

    private final StatusService statusService;

    @Override
    public ApiResult<List<StatusDTO>> getAll(int page, int size) {
        return statusService.getAll(page, size);
    }

    @Override
    public ApiResult<StatusDTO> getOne(Long id) {
        return statusService.getOne(id);
    }

    @Override
    public ApiResult<StatusDTO> add(StatusDTO statusDTO) {
        return statusService.add(statusDTO);
    }

    @Override
    public ApiResult<StatusDTO> update(StatusDTO statusDTO, Long id) {
        return statusService.update(statusDTO, id);
    }

    @Override
    public String delete(Long id) {
        return statusService.delete(id);
    }
}
