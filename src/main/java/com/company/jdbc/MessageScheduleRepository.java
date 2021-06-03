package com.company.jdbc;

import com.company.model.entity.MessageSchedule;
import com.company.util.rowmappers.RowEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MessageScheduleRepository {

    private final String eachRowsSelect = "SELECT * FROM message_schedule";
    private final String rowByDateSelect = "SELECT * FROM message_schedule WHERE message_schedule_date = :date";
    private final String forInsert = "INSERT INTO message_schedule (message_schedule_date, message_schedule_message) VALUES (:date, :message)";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final RowEntryMapper<MessageSchedule> messageScheduleRowEntryMapper;
    private final RowMapper<MessageSchedule> messageScheduleRowSingleMapper;


    public List<MessageSchedule> findAllByDate(Date date) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("date", date);
        return parameterJdbcTemplate.queryForList(rowByDateSelect, parameterSource).stream()
                .map(messageScheduleRowEntryMapper::mapEntryRow)
                .collect(Collectors.toList());

    }

    public List<MessageSchedule> findAll() {
        return jdbcTemplate.query(eachRowsSelect, messageScheduleRowSingleMapper);
    }


    public int save(MessageSchedule messageSchedule) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("date", messageSchedule.getDate());
        parameterSource.addValue("message", messageSchedule.getMessage());
        return parameterJdbcTemplate.update(forInsert, parameterSource);
    }

}
