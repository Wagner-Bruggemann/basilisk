package com.namgrengaw.basilisk.application.infrastructure.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.namgrengaw.basilisk.application.infrastructure.dictionaries.contracts.State;
import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;
import com.namgrengaw.basilisk.application.infrastructure.util.json.JsonUtils;
import io.micrometer.common.util.StringUtils;

import static java.util.Objects.isNull;

public class City {

    private static final String CITIES_ARCHIVE = "com/namgrengaw/basilisk/infrastructure/dictionaries/jsons/cities.json";
    private String name;

    public City(String name, State state) {
        evaluate(name, state);
        this.name = name;
    }

    private void evaluate(String name, State state) {
        if(StringUtils.isBlank(name))
            throw new BusinessLogicException("The city name cannot be null!");
        if(!isValidCity(name, state))
            throw new BusinessLogicException("City not found! The city must be real!");
    }

    public boolean isValidCity(String name, State state) {
        JsonNode citiesNode = JsonUtils.getJson(CITIES_ARCHIVE).get(state.toString());
        if (isNull(citiesNode) || !citiesNode.isArray())  return false;

        for (JsonNode cityNode : citiesNode) {
            final String cityName = cityNode.get("name").asText();
            if (cityName.equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public void update(String name, State state) {
        evaluate(name, state);
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }

}
