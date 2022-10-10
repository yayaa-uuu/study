package com.wx.priority.evaluator.impl;

import com.wx.priority.evaluator.AbstractService;
import com.wx.priority.task.Task;

public class DefaultServiceImpl extends AbstractService {
    public DefaultServiceImpl(Task task) {
        super(task);
    }

    @Override
    protected void execute() {
        //default execute
    }
}
