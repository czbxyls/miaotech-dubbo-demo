package com.miaotech.user.common;

import com.miaotech.common.id.IDGeneratorProvider;
import com.miaotech.user.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IDProviderTest extends BaseTest {

    @Autowired
    private IDGeneratorProvider idGeneratorProvider;

    @Tag("fast")
    @Test
    void idTest() {
        String id = idGeneratorProvider.nextId();
        System.out.println(id);
    }
}
