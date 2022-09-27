package uz.pdp.appnewsstatus.service.interfaces;


import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.entity.News;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.AttachmentDTO;
import uz.pdp.appnewsstatus.payload.NewsDTO;

import java.util.List;

public interface NewsService {

    ApiResult<NewsDTO> getOne(Long id);

    ApiResult<List<NewsDTO>> getAll(int page, int size);

    ApiResult<NewsDTO> add(MultipartHttpServletRequest request,NewsDTO newsDTO);

    ApiResult<NewsDTO> update(MultipartHttpServletRequest request, NewsDTO newsDTO, Long id);

    String delete(Long id);

    News getByIdOrElseThrow(Long id);

}
