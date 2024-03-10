package au.gov.nsw.revenue.booklibrary.config;

import au.gov.nsw.revenue.booklibrary.entity.Status;
import au.gov.nsw.revenue.booklibrary.repository.StatusRepository;
import au.gov.nsw.revenue.booklibrary.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationEventHandler {
  private final StatusRepository statusRepository;

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent(ApplicationReadyEvent event) {
    statusRepository
            .save(Status.builder().id(1).description(Constants.AVAILABLE).build());
    statusRepository
            .save(Status.builder().id(2).description(Constants.BORROWED).build());
  }
}
