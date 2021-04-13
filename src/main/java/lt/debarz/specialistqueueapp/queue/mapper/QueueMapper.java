package lt.debarz.specialistqueueapp.queue.mapper;

import lombok.AllArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import lt.debarz.specialistqueueapp.queue.repository.QueueRepository;
import lt.debarz.specialistqueueapp.queue.exception.EntityNotFoundException;
import lt.debarz.specialistqueueapp.user.model.User;
import lt.debarz.specialistqueueapp.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@AllArgsConstructor
@Component
public class QueueMapper {

    public final Integer QUEUE_WAITING_MINUTES = 5;
    private final QueueRepository queueRepository;
    private final UserRepository userRepository;

    public QueueDto convertQueueEntityToDto(Queue queue) {
        QueueDto queueDto = new QueueDto();
        queueDto.setId(queue.getId());
        queueDto.setQueueNumber(queue.getQueueNumber());
        queueDto.setName(queue.getName());
        queueDto.setLastname(queue.getLastname());
        queueDto.setCreationTime(queue.getRegisterTime());
        queueDto.setRegisterTime(queue.getRegisterTime());
        queueDto.setUserId(queue.getUser().getId());
        queueDto.setSpecialist(queue.getUser().getSpeciality());
        return queueDto;
    }

    public Queue convertDtoToQueueEntity(QueueDto queueDto) {
        Queue queue = new Queue();
        User user = userRepository.getOne(queueDto.getUserId());
        queue.setQueueNumber(generateQueueNumber());
        queue.setName(queueDto.getName());
        queue.setLastname(queueDto.getLastname());
        //creationTime will be create automatically
        queue.setRegisterTime(generateRegistrationTime());
        queue.setUser(user.addQueue(queue));
        return queue;
    }

    private Integer generateQueueNumber() {
        Integer queueNumber = queueRepository.maxQueueNumber();
        long numberOfQueues = queueRepository.count();
        if (numberOfQueues == 0){
            return 1;
        }
        return queueNumber+1;
    }

    private Date generateRegistrationTime(){
        Long maxId = queueRepository.maxId();
        long numberOfQueues = queueRepository.count();
        if (numberOfQueues == 0){
            return addMinutes(new Date(), QUEUE_WAITING_MINUTES);
        }
        Queue queue = queueRepository.findById(maxId).orElseThrow(() -> new EntityNotFoundException(maxId));
        return addMinutes(queue.getRegisterTime(), QUEUE_WAITING_MINUTES);
    }

    private Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

}
