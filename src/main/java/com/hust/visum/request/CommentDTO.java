package com.hust.visum.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    private Long userId;

    private String username;

    private String userAvatar;

    @NotNull
    private Long songId;

    @NotBlank
    private String commentText;

    private String createdAt;


}