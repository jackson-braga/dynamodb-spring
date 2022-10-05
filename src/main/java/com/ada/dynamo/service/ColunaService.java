package com.ada.dynamo.service;

import com.ada.dynamo.mapper.ColunaMapper;
import com.ada.dynamo.repository.ColunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColunaService {

    private final ColunaRepository repository;
    private final ColunaMapper mapper;

    

}
