package com.logistica.data.model.events.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryEvent {
    @JsonProperty("eventName")
    public String eventName = "create-category";

    @JsonProperty("categoryName")
    public String categoryName;
}
