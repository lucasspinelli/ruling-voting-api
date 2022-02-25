package com.voting.ruling.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.voting.ruling.model.Ruling;

import java.lang.reflect.Type;

import static java.util.Objects.isNull;

public class RulingAdapter implements JsonSerializer<Ruling> {
    /**
     * Json object serializer
     * Facilitates object manipulation
     */
    @Override
    public JsonElement serialize(Ruling ruling, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", ruling.getId());
        jsonObject.addProperty("description", ruling.getDescription());

        if(isNull(ruling.getSession())) {
            jsonObject.addProperty("status", "Session is not start yet");
        } else if(ruling.getSession().isActive()){
            jsonObject.addProperty("status", "Session voting in progress");
        } else {
            jsonObject.addProperty("status", "Session is done");
            jsonObject.addProperty("result", ruling.getSession().getResult());
        }
        return jsonObject;
    }
}
