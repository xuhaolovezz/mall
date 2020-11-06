package com.mall.common.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JacksonSerializationStrategy implements RedisTokenStoreSerializationStrategy {

    protected static ObjectMapper objectMapper = new ObjectMapper();

    {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OAuth2Authentication.class,
                new OAuth2AuthenticationJackson2Deserializer(OAuth2Authentication.class));
        objectMapper.registerModule(module);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(bytes,clazz);
        } catch (IOException ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }

    @Override
    public String deserializeString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }

        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }

    }

    @Override
    public byte[] serialize(String data) {
        if (StringUtils.isEmpty(data)) {
            return new byte[0];
        }

        return data.getBytes(StandardCharsets.UTF_8);
    }
}
