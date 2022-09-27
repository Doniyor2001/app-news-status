package uz.pdp.appnewsstatus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.NewsDTO;
import uz.pdp.appnewsstatus.service.interfaces.NewsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsControllerImpl implements NewsController{

    private final NewsService newsService;

    @Override
    public ApiResult<List<NewsDTO>> getAll(int page, int size) {
        return newsService.getAll(page, size);
    }

    @Override
    public ApiResult<NewsDTO> getOne(Long id) {
        return newsService.getOne(id);
    }

    @Override
    public ApiResult<NewsDTO> add(MultipartHttpServletRequest request,NewsDTO newsDTO) {
        return newsService.add(request, newsDTO);
    }

    @Override
    public ApiResult<NewsDTO> update(MultipartHttpServletRequest request, NewsDTO newsDTO, Long id) {
        return newsService.update(request, newsDTO, id);
    }

    @Override
    public String delete(Long id) {
        return newsService.delete(id);
    }
}
