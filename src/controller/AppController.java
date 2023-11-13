package controller;

import model.App;

public enum AppController {

    INSTANCE;
    private final App model;

    AppController() {
        model = new App();
    }

    public App getModel() {
        return model;
    }
}
