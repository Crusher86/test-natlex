package dev.krop.sections.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeologicalObject {

    private String name;

    private String code;
}
