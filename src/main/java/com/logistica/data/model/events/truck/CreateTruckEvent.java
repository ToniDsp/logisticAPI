package com.logistica.data.model.events.truck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateTruckEvent {
    @JsonProperty("eventName")
    public String eventName = "create-truck";

    @JsonProperty ("truckPlate")
    public String truckPlate;

}
