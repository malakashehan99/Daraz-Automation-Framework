package com.daraz.automation.models;

import java.util.List;

public class BaseDataModel<T> {

    private String testId;

    private List<T> testData;

    public String getTestId() {
        return testId;
    }

    public List<T> getTestData() {
        return testData;
    }

}