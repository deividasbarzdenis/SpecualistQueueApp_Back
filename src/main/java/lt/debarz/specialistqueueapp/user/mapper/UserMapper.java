package lt.debarz.specialistqueueapp.user.mapper;

import lombok.AllArgsConstructor;
import lt.debarz.specialistqueueapp.queue.dto.QueueDto;
import lt.debarz.specialistqueueapp.queue.model.Queue;
import lt.debarz.specialistqueueapp.user.dto.UserDto;
import lt.debarz.specialistqueueapp.user.model.Role;
import lt.debarz.specialistqueueapp.user.model.User;
import lt.debarz.specialistqueueapp.user.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDto convertUserToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setClients(user.getClients()
                .stream()
                .map(queue -> getQueueDto(queue))
                .collect(Collectors.toSet()));
        userDto.setRoles(user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet()));
        return userDto;
    }

    public User convertUserDtoToUserEntity(UserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setClients(userDTO.getClients()
                .stream()
                .map(client -> getQueue(user, client))
                .collect(Collectors.toSet()));
        Role role = roleRepository.getOne(2L);
        user.addRole(role);
        return user;
    }

    private QueueDto getQueueDto(Queue queue) {
        QueueDto queueDto = new QueueDto();
        queueDto.setId(queue.getId());
        queueDto.setQueueNumber(queue.getQueueNumber());
        queueDto.setName(queue.getName());
        queueDto.setLastname(queue.getLastname());
        queueDto.setCreationTime(queue.getRegisterTime());
        queueDto.setRegisterTime(queue.getRegisterTime());
        return queueDto;
    }

    private Queue getQueue(User user, QueueDto queueDto) {
        Queue queue = new Queue();
        queue.setName(queueDto.getName());
        queue.setLastname(queueDto.getLastname());
        user.addQueue(queue);
        return queue;
    }
}
