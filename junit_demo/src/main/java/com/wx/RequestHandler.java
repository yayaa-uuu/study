package com.wx;

public interface RequestHandler {
    Response process(Request request) throws Exception;
}
