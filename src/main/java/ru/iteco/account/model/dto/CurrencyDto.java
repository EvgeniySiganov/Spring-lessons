package ru.iteco.account.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@AllArgsConstructor
public class CurrencyDto {

    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    private Integer id;

    @NotBlank(message = "пустой!")
    private String name;
}
