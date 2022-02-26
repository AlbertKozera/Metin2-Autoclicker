package com.alfu.autoclicker.dto;

public enum RecordButtonStatus {
    ENABLE(true),
    DISABLE(false);

    public final boolean status;

    RecordButtonStatus(boolean status) {
        this.status = status;
    }

    public boolean value() {
        return status;
    }
}