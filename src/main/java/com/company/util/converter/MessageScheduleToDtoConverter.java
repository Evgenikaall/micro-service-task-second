package com.company.util.converter;

import com.company.model.dto.MessageScheduleDTO;
import com.company.model.entity.MessageSchedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduleToDtoConverter implements Converter<MessageSchedule, MessageScheduleDTO> {
    @Override
    public MessageScheduleDTO convert(MessageSchedule messageSchedule) {
        return MessageScheduleDTO.builder()
                .date(messageSchedule.getDate())
                .message(messageSchedule.getMessage())
                .build();
    }
}
