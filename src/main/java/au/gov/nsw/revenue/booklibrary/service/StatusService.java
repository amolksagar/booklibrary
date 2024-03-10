package au.gov.nsw.revenue.booklibrary.service;

import au.gov.nsw.revenue.booklibrary.entity.Status;
import au.gov.nsw.revenue.booklibrary.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;
    @Cacheable(value = "statusId", key = "#description")
    public Integer getStatusIdByStatusDescription(String description){
        Status status = statusRepository.findByDescription(description);
        if(status.getId()==null || status.getId()==0){
            throw new RuntimeException("Invalid status requested");
        }
        return status.getId();
    }

    @Cacheable(value = "statusDescription", key = "#statusId")
    public String getStatusDescriptionByStatusId(Integer statusId){
        Optional<Status> statusDescription = statusRepository.findById(statusId);
        if(!statusDescription.isPresent()){
            throw new RuntimeException("Invalid status requested");
        }
        return statusDescription.get().getDescription();
    }
}
