package com.company.controller.listener;

import com.company.model.dto.MessageScheduleDTO;
import com.company.util.processor.ListenerMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListenerRabbitCamel extends RouteBuilder {

    @Produce("seda:listenedData")
    private ProducerTemplate producerTemplate;

    @Value("${spring.rabbitmq.host}")
    private String hostName;

    private final ListenerMessageProcessor listenerMessageProcessor;

    @Override
    public void configure() throws Exception {

        from("timer://foo?fixedRate=true&period=10000")
                .to("rabbitmq://" + hostName + ":5672/exchange?queue=scheduleMessagePostQueue&autoDelete=false");

        from("rabbitmq://" + hostName + ":5672/exchange?queue=scheduleMessagePostQueue&autoDelete=false")
                .filter(bodyAs(String.class).isNotEqualTo(""))
                .unmarshal(new JacksonDataFormat(MessageScheduleDTO.class))
                .process(listenerMessageProcessor)
                .log(">>>> INSERT IN DATABASE")
                .process(exchange -> {
                    final MessageScheduleDTO body = exchange.getIn().getBody(MessageScheduleDTO.class);
                    producerTemplate.asyncSendBody(producerTemplate.getDefaultEndpoint(), body);
                })
                .end();

    }
}
