package lt.debarz.specialistqueueapp.queue.controller;

import lombok.AllArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.mapper.QueueMapper;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import lt.debarz.specialistqueueapp.queue.service.QueueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/queues")
public class QueueController {

    private final QueueService queueService;
    private final QueueMapper queueMapper;

    /**
     * Get all clients in the queue
      */
    @GetMapping
    public List<QueueDto> getAllClientsInQueue() {
        return queueService.getAllClients();
    }

    /**
     * Add client data to DB and ad client to the queue
     */
    @PostMapping
    public ResponseEntity<QueueDto> addClientToQueue(@RequestBody @Valid QueueDto queueDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(queueService.addToQueue(queueDto));
    }

    /**
     * Get client in the queue by id
     */
    @GetMapping("/{id}")
    public QueueDto getClientInQueue(@PathVariable long id) {
        return queueService.getQueueById(id);
    }

    /**
     * Delete client from queue by id
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientInQueue(@PathVariable long id){
        queueService.deleteQueue(id);
    }

    /**
     * Update client in the queue
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public QueueDto updateClientInQueue(@RequestBody @Valid QueueDto queueDto) {
        return queueService.updateQueue(queueDto);
    }

    /**
     * Get client by lastname
     */
    @GetMapping("/clientinqueue/{lastname}")
    public QueueDto getClientInTheQueueByLastname(@PathVariable  String lastname){
        Queue queue = queueService.findQueueClientByLastname(lastname);
        return queueMapper.convertQueueEntityToDto(queue);
    }

    /**
     * Get client by queue number
     */
    @GetMapping("/client/{queueNumber}")
    public QueueDto getClientInTheQueueByQueueNumber(@PathVariable  int queueNumber){
        Queue queue = queueService.findClientByQueueNumber(queueNumber);
        return queueMapper.convertQueueEntityToDto(queue);
    }

    /**
     * Get clients in the queue by registration date [registration date is greater then current time]
     */
    @GetMapping("/clients/{pageNumber}")
    public List<QueueDto> findAllProductsQuery(@PathVariable("pageNumber") int pageNumber) {
        List<Queue> clients = queueService.findRegistrationTime(pageNumber, 5);
        return clients.stream()
                .map(queueMapper::convertQueueEntityToDto)
                .collect(Collectors.toList());
    }
}
