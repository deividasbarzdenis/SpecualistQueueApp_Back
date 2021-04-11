package lt.debarz.specialistqueueapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.user.model.Role;
import lt.debarz.specialistqueueapp.user.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;

    @NotBlank
    @Size(min=3, max=20)
    private String username;

    @NotBlank
    @Size(min=3, max=20)
    private String password;

    @Size(min=3, max=20)
    private String lastname;

    @Size(min=3, max=20)
    private String name;

    private String email;

    private String phone;

    private String speciality;

    private Set<QueueDto> clients = new HashSet<>();

    private Set<String> roles = new HashSet<>();

    //response after successful login
    public UserDto(User user) {
        this.id = user.getId();
        this.username=user.getUsername();
        this.name=user.getName();
        this.lastname =user.getLastname();
        this.email = user.getEmail();
        this.speciality=user.getSpeciality();
        this.phone = user.getPhone();
        this.roles=user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }
}
