package com.paymentApplication.mapper;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface AccountMapper {

    @Mapping(target = "user", source = "userDto")
    Account mapToEntity(AccountDto accountDto);

    @Mapping(target = "userDto", source = "user")
    AccountDto mapToDto(Account account);
}
