package au.gov.nsw.revenue.booklibrary.service;


import au.gov.nsw.revenue.booklibrary.entity.Status;
import au.gov.nsw.revenue.booklibrary.repository.StatusRepository;
import au.gov.nsw.revenue.booklibrary.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Slf4j
public class ServiceStatusTest {

    @InjectMocks
    private StatusService statusService;

    @Mock
    private StatusRepository statusRepository;

    @Test
    void testGetStatusIdByStatusDescription(){
        when(statusRepository.findByDescription(Constants.AVAILABLE)).thenReturn(Status.builder().description(Constants.AVAILABLE).id(1).build());
        Integer id = statusService.getStatusIdByStatusDescription(Constants.AVAILABLE);
        assertNotNull(id);
        assertEquals(1,id);
    }

    @Test
    void testGetStatusDescriptionById(){
        when(statusRepository.findById(1)).thenReturn(Optional.of(Status.builder().description(Constants.AVAILABLE).id(1).build()));
        String description = statusService.getStatusDescriptionByStatusId(1);
        assertNotNull(description);
        assertEquals(Constants.AVAILABLE,description);
    }
}
