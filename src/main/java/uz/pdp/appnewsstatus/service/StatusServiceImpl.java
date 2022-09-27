package uz.pdp.appnewsstatus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appnewsstatus.Repository.StatusRepository;
import uz.pdp.appnewsstatus.entity.Status;
import uz.pdp.appnewsstatus.exeption.RestException;
import uz.pdp.appnewsstatus.payload.ApiResult;
import uz.pdp.appnewsstatus.payload.StatusDTO;
import uz.pdp.appnewsstatus.service.interfaces.StatusService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public ApiResult<StatusDTO> getOne(Long id) {
        Status status = getByIdOrElseThrow(id);
        StatusDTO statusDTO = entityToInfoDTO(status);
        return ApiResult.successResponse(statusDTO);
    }

    @Override
    public ApiResult<List<StatusDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Status> statusPage = statusRepository.findAll(pageable);
        List<Status> status = statusPage.getContent();
        List<StatusDTO> statusDTOS = status
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(statusDTOS);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResult<StatusDTO> add(StatusDTO statusDTO) {

        Status status = new Status(
                statusDTO.getName(),
                statusDTO.getNumber()
        );
        statusRepository.save(status);
        return returnApiResult(status);
    }

    @Override
    public ApiResult<StatusDTO> update(StatusDTO statusDTO, Long id) {
        Status status = getByIdOrElseThrow(id);
        status.setName(statusDTO.getName());
        status.setNumber(statusDTO.getNumber());
        statusRepository.save(status);
        return returnApiResult(status);
    }

    @Override
    public String delete(Long id) {
        Status status = getByIdOrElseThrow(id);
        statusRepository.delete(status);
        return "Successfully deleted";
    }

    @Override
    public Status getByIdOrElseThrow(Long id) {
        return statusRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Status")
        );
    }

    private ApiResult<StatusDTO> returnApiResult(Status status) {
        StatusDTO statusDTO = new StatusDTO(
                status.getId(),
                status.getName(),
                status.getNumber()
        );
        return new ApiResult<>(statusDTO, true);
    }



    private StatusDTO entityToInfoDTO(Status status) {
        return new StatusDTO(
                status.getId(),
                status.getName(),
                status.getNumber()
        );
    }
}
