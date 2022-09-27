package uz.pdp.appnewsstatus.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.NewsDTO;
import uz.pdp.appnewsstatus.utils.AppConstant;

import java.util.List;

@RequestMapping(NewsController.NEWS_CONTROLLER_PATH)
public interface NewsController {

    String NEWS_CONTROLLER_PATH = AppConstant.BASE_PATH + "/news/";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";

    @GetMapping(VIEW)
    ApiResult<List<NewsDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size);
    @GetMapping(VIEW+"/{id}")
    ApiResult<NewsDTO> getOne(@PathVariable Long id);

    @PostMapping(ADD)
    ApiResult<NewsDTO> add(MultipartHttpServletRequest request,@RequestBody NewsDTO newsDTO);

    @PutMapping(UPDATE + "/{id}")
    ApiResult<NewsDTO> update(MultipartHttpServletRequest request, @RequestBody NewsDTO newsDTO, @PathVariable Long id);

    @DeleteMapping(DELETE+"/{id}")
    String delete(@PathVariable Long id);

}
