package lt.debarz.specialistqueueapp.queue.model;

import lombok.*;
import lt.debarz.specialistqueueapp.user.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private Integer queueNumber;

    @Column(nullable = false)
    @NotBlank
    @Size(min=3, max=254)
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(min=3, max=254)
    private String lastname;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime = new Date();

    //it need to be calculated then mapping dto to entity
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    @ManyToOne
    private User user;

}
