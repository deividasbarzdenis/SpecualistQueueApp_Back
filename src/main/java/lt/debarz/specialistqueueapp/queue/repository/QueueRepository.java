package lt.debarz.specialistqueueapp.queue.repository;

import lt.debarz.specialistqueueapp.queue.model.Queue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface QueueRepository extends JpaRepository<Queue, Long> {

    /**
     * to find biggest id, also it get be used to find last and add 5 minutes for next client:
     */
    @Query(value = "select max(id) from Queue")
    Long maxId();

    /**
     * find recent registration date, it used to find last and add 5 minutes for next client [impl in service]:
     */
  /*  @Query(value = "select max(registerTime) from Queue")
    Date maxRegisterTime();*/

    @Query(value = "select max(queueNumber) from Queue")
    Integer maxQueueNumber();

    /**
     * Get max value for maxQueueNumber, maxRegisterTime, maxId
     */
/*    @Query(value = "select  max(t.type) from #{#entityName} t")
    <T> T maxValue();*/

    /**
     *  Get clients in the queue by registration time (order ascending, but not less then current time ).
     */
    @Query("select a from Queue a where a.registerTime > CURRENT_TIME ")
    List<Queue> findAllRegistrationDatesNotLessThenCurrentTime(Pageable pageable);

    Queue findByLastname(String lastname);

    Queue findByQueueNumber(Integer queueNumber);

}
