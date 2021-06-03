package com.company.util.rowmappers;

import com.company.model.entity.MessageSchedule;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Map;

@Component
public class MessageScheduleEntryRowMapper implements RowEntryMapper<MessageSchedule> {
    @Override
    public MessageSchedule mapEntryRow(Map<String, Object> entry) {
        return MessageSchedule.builder()
                .id((Long) entry.get("message_schedule_id"))
                .date((Date) entry.get("message_schedule_date"))
                .message((String) entry.get("message_schedule_message"))
                .build();
    }
}
