package com.daraz.automation.tests.base;

import com.daraz.automation.models.BaseDataModel;
import com.daraz.automation.models.SearchData;
import com.daraz.automation.utils.JsonReader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "commonDataProvider")

    public Iterator<Object[]> commonDataProvider(Method method) {

        // Get TC ID from @Test description
        Test testAnnotation = method.getAnnotation(Test.class);
        String testCaseId = testAnnotation.description();

        // Load JSON data
        List<BaseDataModel<SearchData>> allData =
                JsonReader.readJsonData(
                        "src/main/resources/testdata/searchData.json",
                        new TypeReference<
                                List<BaseDataModel<SearchData>>>() {}
                );

        // Filter matching TC ID
        return allData.stream()
                .filter(node ->
                        node.getTestId()
                                .equalsIgnoreCase(testCaseId)
                )
                .findFirst()
                .map(node ->
                        node.getTestData().stream()
                                .map(data ->
                                        new Object[]{data}
                                )
                                .toList()
                )
                .orElse(Collections.emptyList())
                .iterator();
    }
}