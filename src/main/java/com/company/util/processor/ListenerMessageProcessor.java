package com.company.util.processor;

import com.company.model.dto.MessageScheduleDTO;
import com.company.service.MessageScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListenerMessageProcessor implements Processor {

    private final MessageScheduleService messageScheduleService;

    @Override
    public void process(Exchange exchange) throws Exception {
        messageScheduleService.saveMessage(exchange.getIn().getBody(MessageScheduleDTO.class));
    }
}
