package com.logistica.data.model.events.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateWarehouseEvent {
    @JsonProperty("eventName")
    public String eventName = "create-warehouse";

    @JsonProperty("manager")
    public String manager;

    @JsonProperty("name_warehouse")
    public String name;
}
