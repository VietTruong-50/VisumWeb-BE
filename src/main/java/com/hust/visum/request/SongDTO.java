package com.hust.visum.request;

import lombok.Data;

@Data
public class SongDTO {
    private String songName;

    private int duration;

    private byte[] image;

    private String imageName;

    private Long singer_id;

    private Long composer_id;

    private Long subCategory_id;
}
