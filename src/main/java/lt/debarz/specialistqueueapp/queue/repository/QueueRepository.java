package lt.debarz.specialistqueueapp.queue.repository;

import lt.debarz.specialistqueueapp.queue.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    List<Queue> findAllByRegisterTime(Date publicationDate);

    List<Queue> findAllByRegisterTimeBetween(
            Date registerTimeStart,
            Date registerTimeEnd);

    @Query("select a from Queue a where a.registerTime <= :registerTime")
    List<Queue> findAllWithRegisterTimeBefore(
            @Param("registerTime") Date registerTime);

    //to find biggest id, it used to find last and add 5 minutes for next client:
    @Query(value = "select max(id) from Queue")
    public Long maxId();

    //find recent registration date, it used to find last and add 5 minutes for next client:
    @Query(value = "select max(registerTime) from Queue")
    public Date maxRegisterTime();

    // prideti metoda, kur rodytu monituryje 5 pirmus eileje, rusiuotu pagal registracijos data (ascending, but not
    // less then .now() ).


}
