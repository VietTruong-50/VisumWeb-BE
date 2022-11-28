package com.hust.visum.request;

import lombok.Data;

@Data
public class PasswordDTO {
    String currentPassword;

    String newPassword;

    String cf_password;

}
