package lt.debarz.specialistqueueapp.queue.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class QueueMapper {

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
        //queue.setQueueNumber();//reikia metodo, kad skaiciuotu eiles numeri
        queue.setName(queueDto.getName());
        queue.setLastname(queue.getLastname());
        //creationTime will be create automatically
        //queue.setRegisterTime();//reikia metodo, kad skaiciuotu registracijos laika
        return queue;
    }
}
