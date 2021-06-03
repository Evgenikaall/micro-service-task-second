package com.company.service;

import com.company.jdbc.MessageScheduleRepository;
import com.company.model.dto.MessageScheduleDTO;
import com.company.model.entity.MessageSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.sql.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MessageScheduleService {

    private final MessageScheduleRepository messageScheduleRepository;
    private final Converter<MessageSchedule, MessageScheduleDTO> toDTO;
    private final Converter<MessageScheduleDTO, MessageSchedule> toEntity;


    public int saveMessage(MessageScheduleDTO messageScheduleDTO){
        return messageScheduleRepository.save(toEntity.convert(messageScheduleDTO));
    }


    public List<MessageScheduleDTO> findAllMessages() {
        return messageScheduleRepository.findAll().stream().map(toDTO::convert).collect(toList());
    }

    public List<MessageScheduleDTO> findAllMessagesByDate(String date) throws ValidationException {
            return messageScheduleRepository.findAllByDate(Date.valueOf(date)).stream().map(toDTO::convert).collect(toList());
    }


}
