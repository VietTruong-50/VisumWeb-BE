package com.hust.visum.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hust.visum.Enum.GenderEnum;
import com.hust.visum.model.Role;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    private String userName;

    private String password;

    private GenderEnum gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date birthOfDate;

    private String firstName;

    private String lastName;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String mobile;

    private String email;

    private Set<String> roles;

    public User toUser(User user){

        user.setUserName(userName);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setGenderEnum(gender);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setBirthOfDate(birthOfDate);

        return user;
    }
}
