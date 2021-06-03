package com.company.util.processor;

import com.company.model.dto.MessageScheduleDTO;
import com.company.service.EncoderService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class EncodingMessageProcessor implements Processor {
    private final EncoderService encoderService;

    @Override
    public void process(Exchange exchange) throws Exception {
        final MessageScheduleDTO body = exchange.getIn().getBody(MessageScheduleDTO.class);
        exchange.getIn().setBody(encoderService.encode(body), String.class);
    }
}
