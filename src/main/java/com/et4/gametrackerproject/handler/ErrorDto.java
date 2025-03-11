package com.et4.gametrackerproject.handler;

import com.et4.gametrackerproject.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Permet de strocker l'ensemble des codes erreurs
public class ErrorDto {
    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    @Builder.Default
    private List<String> errors = new ArrayList<>();


}
