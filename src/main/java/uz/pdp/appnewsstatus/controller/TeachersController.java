package uz.pdp.appnewsstatus.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.TeachersDTO;
import uz.pdp.appnewsstatus.utils.AppConstant;

import java.util.List;

@RequestMapping(TeachersController.TEACHERS_CONTROLLER_PATH)
public interface TeachersController {

    String TEACHERS_CONTROLLER_PATH = AppConstant.BASE_PATH + "/teachers/";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";

    @GetMapping(VIEW)
    ApiResult<List<TeachersDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size);
    @GetMapping(VIEW+"/{id}")
    ApiResult<TeachersDTO> getOne(@PathVariable Long id);

    @PostMapping(ADD)
    ApiResult<TeachersDTO> add(MultipartHttpServletRequest request,@RequestBody TeachersDTO teachersDTO);

    @PutMapping(UPDATE + "/{id}")
    ApiResult<TeachersDTO> update(MultipartHttpServletRequest request, @RequestBody TeachersDTO teachersDTO, @PathVariable Long id);

    @DeleteMapping(DELETE+"/{id}")
    String delete(@PathVariable Long id);

}
