package com.example.meraApi.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StopDto {

    private Long id;

    private String stopName;

}