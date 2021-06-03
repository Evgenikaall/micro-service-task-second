package com.company.controller.receiver;

import com.company.util.processor.EncodingMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiverRabbitCamel extends RouteBuilder {

    private final EncodingMessageProcessor encodingMessageProcessor;

    @Value("${spring.rabbitmq.host}")
    private String hostName;

    @Override
    public void configure() throws Exception {
        from("seda:listenedData")
                .process(encodingMessageProcessor)
                .log(">>>>>>> IS ENCODED")
                .marshal(new JacksonDataFormat(String.class))
                .log(">>>>>>> SEND SUCCESS")
                .to("rabbitmq://" + hostName + ":5672/outExchange?queue=encodedScheduleMessagePostQueue&autoDelete=false");
    }
}
