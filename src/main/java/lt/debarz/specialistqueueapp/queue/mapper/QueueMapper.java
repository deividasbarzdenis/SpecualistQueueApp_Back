package lt.debarz.specialistqueueapp.queue.mapper;

import lombok.AllArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import lt.debarz.specialistqueueapp.queue.repository.QueueRepository;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@AllArgsConstructor
@Component
public class QueueMapper {

    public final Integer QUEUE_WAITING_MINUTES = 5;
    private final QueueRepository queueRepository;

    public QueueDto covertQueueEntityToDto(Queue queue) {
        QueueDto queueDto = new QueueDto();
        queueDto.setId(queue.getId());
        queueDto.setQueueNumber(queue.getQueueNumber());
        queueDto.setName(queue.getName());
        queueDto.setLastname(queue.getLastname());
        queueDto.setCreationTime(queue.getRegisterTime());
        queueDto.setRegisterTime(queue.getRegisterTime());
        return queueDto;
    }

    public Queue convertDtoToQueueEntity(QueueDto queueDto) {
        Queue queue = new Queue();
        queue.setQueueNumber(generateQueueNumber());
        queue.setName(queueDto.getName());
        queue.setLastname(queue.getLastname());
        //creationTime will be create automatically
        queue.setRegisterTime(generateRegistrationTime());
        return queue;
    }

    private Integer generateQueueNumber() {
        Integer queueNumber = queueRepository.maxQueueNumber();
        if (queueNumber == null){
            return 1;
        }
        return queueNumber+1;
    }

    private Date generateRegistrationTime(){
        Date reTime = queueRepository.maxRegisterTime();
        if (reTime == null){
            return addMinutes(new Date(), QUEUE_WAITING_MINUTES);
        }
        return addMinutes(reTime, QUEUE_WAITING_MINUTES);
    }

    private Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
