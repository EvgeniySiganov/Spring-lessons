package ru.iteco.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Currency;
import ru.iteco.account.validation.Update;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    Integer id;

    @NotNull
    Integer userId;

    @NotBlank(message = "пустой!")
    String number;

    @PositiveOrZero
    @NotNull
    BigDecimal amount;

    @Currency
    @NotNull
    String currency;
}
