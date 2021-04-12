package lt.debarz.specialistqueueapp.queue.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id) {
        super("Queue with id: " + id + " not found!");
    }
}
