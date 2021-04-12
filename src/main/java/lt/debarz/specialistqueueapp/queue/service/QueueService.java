package lt.debarz.specialistqueueapp.queue.service;

import lombok.AllArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.mapper.QueueMapper;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import lt.debarz.specialistqueueapp.queue.repository.QueueRepository;
import lt.debarz.specialistqueueapp.user.exception.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final QueueMapper queueMapper;

    /**
     * Get all clients in queue:
     */
    public List<QueueDto> getAllClients() {
        return queueRepository
                .findAll()
                .stream()
                .map(q -> queueMapper.convertQueueEntityToDto(q))
                .collect(Collectors.toList());
    }

    /**
     * add client to queue
     */
    public QueueDto addToQueue(QueueDto queueDto) {
        Queue queue = queueMapper.convertDtoToQueueEntity(queueDto);
        Queue saveQueue = queueRepository.save(queue);
        queueDto.setId(saveQueue.getId());
        return queueDto;
    }

    /**
     * Get client in the queue by id
     */
    public QueueDto getQueueById(long id) {
        Queue queue = getById(id);
        return queueMapper.convertQueueEntityToDto(queue);
    }

    /**
     * Delete client from queue
     */
    public void deleteQueue(long id) {
        if(!queueRepository.existsById(id)){
            throw new EntityNotFoundException(id);
        }
        queueRepository.deleteById(id);
    }

    /**
     * Update client in the queue
     */
    public QueueDto updateQueue(QueueDto queueDto) {
        Long id = queueDto.getId();
        if(id == null){
            throw new EntityNotFoundException(id);
        }
        Queue queue = getById(id);
        BeanUtils.copyProperties(queueDto, queue);
        queueRepository.save(queue);
        return queueDto;
    }

    /**
     * get client in the queue by lastname
     */
    public Queue findQueueClientByLastname(String lastname){
        return queueRepository.findByLastname(lastname);
    }

    /**
     * get client in queue by queue number
     */
    public Queue findClientByQueueNumber(Integer queueNumber){
        return queueRepository.findByQueueNumber(queueNumber);
    }

    /**
     * Get first 5 client by registration date-time, reDate not less current time, order ascending
     */
    public List<Queue> findRegistrationTime(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("registerTime").ascending());
        return queueRepository.findAllRegistrationDatesNotLessThenCurrentTime(pageable);
    }

    /**
     * get clint by id method - reusable/private
     */
    private Queue getById(long id){
        return queueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }


}
