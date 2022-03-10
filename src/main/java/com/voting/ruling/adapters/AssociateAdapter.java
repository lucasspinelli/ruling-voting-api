package com.voting.ruling.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.voting.ruling.model.Associate;

import java.lang.reflect.Type;

public class AssociateAdapter implements JsonSerializer<Associate> {
    @Override
    public JsonElement serialize(Associate associate, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", associate.getId());
        jsonObject.addProperty("name", associate.getName());
        jsonObject.addProperty("lastName", associate.getLastName());
        jsonObject.addProperty("cpf", associate.getCpf());

        return jsonObject;
    }
}
