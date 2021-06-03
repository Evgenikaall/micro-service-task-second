package com.company.service;

import com.company.model.dto.MessageScheduleDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Service
public class EncoderService implements Encoder<MessageScheduleDTO> {

    private final Key key = Keys.secretKeyFor(HS256);

    @Override
    public String encode(MessageScheduleDTO subject) {
        return Jwts.builder().setSubject(subject.toString()).signWith(key).compact();
    }
}
