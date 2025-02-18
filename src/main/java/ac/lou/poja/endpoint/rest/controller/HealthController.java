package ac.lou.poja.endpoint.rest.controller;

import static java.util.UUID.randomUUID;

import ac.lou.poja.PojaGenerated;
import ac.lou.poja.endpoint.event.EventProducer;
import ac.lou.poja.endpoint.event.gen.UuidCreated;
import ac.lou.poja.repository.DummyRepository;
import ac.lou.poja.repository.DummyUuidRepository;
import ac.lou.poja.repository.model.Dummy;
import ac.lou.poja.repository.model.DummyUuid;
import java.util.List;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@Value
public class HealthController {

  DummyRepository dummyRepository;
  DummyUuidRepository dummyUuidRepository;
  EventProducer eventProducer;

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/dummy-table")
  public List<Dummy> dummyTable() {
    return dummyRepository.findAll();
  }

  @GetMapping(value = "/uuid-created")
  public String uuidCreated() throws InterruptedException {
    var randomUuid = randomUUID().toString();
    var event = new UuidCreated().toBuilder().uuid(randomUuid).build();

    eventProducer.accept(List.of(event));

    Thread.sleep(20_000);
    return dummyUuidRepository.findById(randomUuid).map(DummyUuid::getId).orElseThrow();
  }
  @getMapping("/generate-odd-number")
  public int generateoddNumber(){
    Random random = new Radom();
    int radomNumber =random.NextInt(50)*2+1;
    return radomNumber;
  }
}
