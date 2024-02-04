package com.gothings.services.crud;

import java.util.Optional;

public interface ICrudService<REQUEST_DTO, RESPONSE_DTO, ID> {
    //ID save(ID id, REQUEST_DTO requestDto);

    ID save(REQUEST_DTO requestDto);

    ID update(ID id, REQUEST_DTO requestDto);

    void deleteById(ID id);

    Optional<RESPONSE_DTO> findById(ID id);
}
