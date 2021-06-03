package com.company.util.rowmappers;

import com.company.model.entity.MessageSchedule;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MessageScheduleSingleRowMapper implements RowMapper<MessageSchedule> {
    @Override
    public MessageSchedule mapRow(ResultSet resultSet, int i) throws SQLException {
        return MessageSchedule.builder()
                .id(resultSet.getLong("message_schedule_id"))
                .date(resultSet.getDate("message_schedule_date"))
                .message(resultSet.getString("message_schedule_message"))
                .build();
    }
}
