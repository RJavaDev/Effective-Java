package com.example.uplode_file.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttachResponseDTO {
    private String id;
    private String originalName;
    private String path;
    private Long size;
    private String extension;
    private Double duration;
    private LocalDateTime createdData;
    private String url;
}
