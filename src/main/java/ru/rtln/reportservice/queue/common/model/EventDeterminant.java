package ru.rtln.reportservice.queue.common.model;

import lombok.Getter;

@Getter
public enum EventDeterminant {

    CREATE("OBJECT_CREATED");

    private final String eventName;

    /**
     * @param eventName fixed event name
     */
    EventDeterminant(String eventName) {
        this.eventName = eventName;
    }

}
