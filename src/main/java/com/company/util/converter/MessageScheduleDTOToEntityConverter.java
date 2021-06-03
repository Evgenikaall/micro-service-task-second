package com.company.util.converter;

import com.company.model.dto.MessageScheduleDTO;
import com.company.model.entity.MessageSchedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduleDTOToEntityConverter implements Converter<MessageScheduleDTO, MessageSchedule> {
    @Override
    public MessageSchedule convert(MessageScheduleDTO messageScheduleDTO) {
        return MessageSchedule.builder()
                .message(messageScheduleDTO.getMessage())
                .date(messageScheduleDTO.getDate())
                .build();
    }
}
