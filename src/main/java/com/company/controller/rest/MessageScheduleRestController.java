package com.company.controller.rest;

import com.company.model.dto.MessageScheduleDTO;
import com.company.service.MessageScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v2/message")
@RequiredArgsConstructor
public class MessageScheduleRestController {

    private final MessageScheduleService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<MessageScheduleDTO> findAllMessages(){
        return service.findAllMessages();
    }

    @SneakyThrows
    @GetMapping("/{date}")
    @ResponseStatus(OK)
    public List<MessageScheduleDTO> findAllMessagesByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date){
        return service.findAllMessagesByDate(date);
    }


}
