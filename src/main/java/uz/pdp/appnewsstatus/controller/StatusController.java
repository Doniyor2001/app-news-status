package uz.pdp.appnewsstatus.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.StatusDTO;
import uz.pdp.appnewsstatus.utils.AppConstant;

import java.util.List;

@RequestMapping(StatusController.STATUS_CONTROLLER_PATH)
public interface StatusController {

    String STATUS_CONTROLLER_PATH = AppConstant.BASE_PATH + "/status/";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";

    @GetMapping(VIEW)
    ApiResult<List<StatusDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size);
    @GetMapping(VIEW+"/{id}")
    ApiResult<StatusDTO> getOne(@PathVariable Long id);

    @PostMapping(ADD)
    ApiResult<StatusDTO> add(@RequestBody StatusDTO statusDTO);

    @PutMapping(UPDATE + "/{id}")
    ApiResult<StatusDTO> update(@RequestBody StatusDTO statusDTO, @PathVariable Long id);

    @DeleteMapping(DELETE+"/{id}")
    String delete(@PathVariable Long id);

}
