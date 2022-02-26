package com.alfu.autoclicker.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PathData {

    private String id;
    private String description;
    private List<Point> points;

}
