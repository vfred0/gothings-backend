package com.gothings.services.crud;

import com.gothings.data.daos.IRepository;
import com.gothings.data.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public abstract class CrudService<ENTITY, REQUEST_DTO, RESPONSE_DTO, ID> implements ICrudService<REQUEST_DTO, RESPONSE_DTO, ID> {
    private final Mapper mapper;
    private final IRepository<ENTITY, ID> repository;
    private final Class<ENTITY> entityClass;
    private final Class<RESPONSE_DTO> responseClass;

    @Override
    @Transactional
    public ID save(REQUEST_DTO requestDto) {
        return this.getId(this.repository.save(mapper.toEntity(requestDto, entityClass)));
    }

    @Override
    @Transactional
    public ID update(ID id, REQUEST_DTO requestDto) {
        this.findById(id);
        return this.getId(this.repository.save(mapper.toEntity(requestDto, entityClass)));
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override

    public Optional<RESPONSE_DTO> findById(ID id) {
        return repository.findById(id).map(entity -> mapper.toDto(entity, responseClass));
    }

    protected abstract RuntimeException getMessageException();

    protected abstract ID getId(ENTITY entity);

}