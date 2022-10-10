package com.wx.priority.evaluator.impl;

import com.wx.priority.evaluator.AbstractService;
import com.wx.priority.task.Task;

public class PriorityServiceImpl extends AbstractService implements Comparable<PriorityServiceImpl> {
    public PriorityServiceImpl(Task task) {
        super(task);
    }

    @Override
    protected void execute() {
        //execute priority
    }


    @Override
    public int compareTo(PriorityServiceImpl o) {
        return getPriority() - o.getPriority();
    }
}
