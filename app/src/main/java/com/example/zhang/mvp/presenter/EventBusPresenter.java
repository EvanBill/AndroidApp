package com.example.zhang.mvp.presenter;

import com.example.zhang.base.BasePresenter;
import com.example.zhang.mvp.contract.EventBusContract;
import com.example.zhang.mvp.model.EventBusModel;

public class EventBusPresenter extends BasePresenter<EventBusContract.IEventBusView, EventBusModel> {
    public EventBusPresenter(EventBusContract.IEventBusView view) {
        super(view);
        model = new EventBusModel();
    }
}
