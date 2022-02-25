package com.voting.ruling.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.voting.ruling.model.Session;
import com.voting.ruling.utils.DateUtils;

import java.lang.reflect.Type;
import java.util.Date;

import static java.util.Objects.nonNull;

public class SessionAdapter implements JsonSerializer<Session> {
    @Override
    public JsonElement serialize(Session session, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", session.getId());

        if (nonNull(session.getRuling())) {
            jsonObject.addProperty("rulingId", session.getRuling().getId());
            jsonObject.addProperty("ruling", session.getRuling().getDescription());
        }
        jsonObject.addProperty("startDate", DateUtils.dateToBrazilianLayout(session.getCreationDate()));
        jsonObject.addProperty("endDate", DateUtils.dateToBrazilianLayout(new Date((long) (session.getCreationDate().getTime() + session.getExpiration()))));
        jsonObject.addProperty("status", session.isActive() ? "Session in progress" : "Session is done");
        jsonObject.addProperty("result", session.getResult());

        return jsonObject;
    }
}
