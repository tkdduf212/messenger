package com.noint.messenger.entity;

import lombok.Data;

import java.util.List;

public class Message {
    @Data
    public static class MessageRequestForm {
        private String msg;
        private List<String> targets;
    }
}
