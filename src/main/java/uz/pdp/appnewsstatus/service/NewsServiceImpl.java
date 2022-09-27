package uz.pdp.appnewsstatus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appnewsstatus.Repository.NewsRepository;
import uz.pdp.appnewsstatus.entity.Attachment;
import uz.pdp.appnewsstatus.entity.News;
import uz.pdp.appnewsstatus.exeption.RestException;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.AttachmentDTO;
import uz.pdp.appnewsstatus.payload.NewsDTO;
import uz.pdp.appnewsstatus.service.interfaces.AttachmentService;
import uz.pdp.appnewsstatus.service.interfaces.NewsService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final AttachmentService attachmentService;
    @Override
    public ApiResult<NewsDTO> getOne(Long id) {
        News news = getByIdOrElseThrow(id);
        NewsDTO newsDTO = entityToInfoDTO(news);
        return ApiResult.successResponse(newsDTO);
    }

    @Override
    public ApiResult<List<NewsDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsPage = newsRepository.findAll(pageable);
        List<News> news = newsPage.getContent();
        List<NewsDTO> newsDTOS = news
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(newsDTOS);
    }

    private NewsDTO entityToInfoDTO(News news){
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getText(),
                news.getDate(),
                news.getAttachment() == null ? null : news.getAttachment().getId()
        );
    }

    private ApiResult<NewsDTO> returnApiResult(News news){
        NewsDTO newsDTO = new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getText(),
                news.getDate(),
                news.getAttachment() == null ? null : news.getAttachment().getId()
        );
        return new ApiResult<>(newsDTO,true);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResult<NewsDTO> add(MultipartHttpServletRequest request, NewsDTO newsDTO) {

        Attachment attachment = attachmentService.uploadAttachment(request);

        LocalDateTime date = LocalDateTime.now();
        if (!Objects.isNull(newsDTO.getDate())){
            date = newsDTO.getDate();
        }
        News news = new News(
                newsDTO.getTitle(),
                newsDTO.getText(),
                date,
                attachment

        );
        newsRepository.save(news);
        return returnApiResult(news);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResult<NewsDTO> update(MultipartHttpServletRequest request, NewsDTO newsDTO, Long id) {
        Attachment attachment = attachmentService.uploadAttachment(request);
        LocalDateTime date = LocalDateTime.now();
        if (!Objects.isNull(newsDTO.getDate())){
            date = newsDTO.getDate();
        }
        News news = getByIdOrElseThrow(id);
        news.setTitle(newsDTO.getTitle());
        news.setText(news.getText());
        news.setDate(date);
        news.setAttachment(attachment);
        newsRepository.save(news);
        return returnApiResult(news);
    }

    @Override
    public String delete(Long id) {
        News news = getByIdOrElseThrow(id);
        newsRepository.delete(news);
        return "Successfully deleted";
    }

    @Override
    public News getByIdOrElseThrow(Long id) {
        return newsRepository.findById(id).orElseThrow(
                () -> RestException.notFound("News")
        );
    }
}
