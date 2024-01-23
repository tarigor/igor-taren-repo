package com.senla.adsservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvCommentDto {
    private long advId;
    @NotEmpty
    @Size(min = 3, max = 50, message = "`commentMakerName` must be between {min} and {max} characters long")
    private String commentMakerName;
    @NotEmpty
    @Size(min = 3, max = 255, message = "`commentText` must be between {min} and {max} characters long")
    private String commentText;
}
