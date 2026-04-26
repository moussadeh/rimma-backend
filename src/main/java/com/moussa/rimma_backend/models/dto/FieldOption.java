package com.moussa.rimma_backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldOption {

    private String name;
    private String label;
    private String type;
}
