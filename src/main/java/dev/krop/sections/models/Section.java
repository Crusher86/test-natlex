package dev.krop.sections.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    private String name;

    private List<GeologicalObject> geologicalObjects;
}
