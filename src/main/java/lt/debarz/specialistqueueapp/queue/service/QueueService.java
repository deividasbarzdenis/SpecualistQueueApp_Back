package lt.debarz.specialistqueueapp.queue.service;

import lombok.AllArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.mapper.QueueMapper;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import lt.debarz.specialistqueueapp.queue.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final QueueMapper queueMapper;

    //get all clients in queue:
    public List<QueueDto> getAllClients() {
        return queueRepository
                .findAll()
                .stream()
                .map(q -> queueMapper.covertQueueEntityToDto(q))
                .collect(Collectors.toList());
    }

    // add client to queue

    // get client by id

    //delete client

    //update client

    // get last client by registration date/time [yyyy:MM:dd HH:mm:ss]

    // get first 5 client by registration date-time, reDate have to be not less time.now(), order ascending

    //get clint by id method - reusable/private

    //calculate registration date

}
