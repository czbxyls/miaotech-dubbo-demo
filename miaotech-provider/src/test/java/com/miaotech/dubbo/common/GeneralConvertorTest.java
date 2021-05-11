package com.miaotech.dubbo.common;

import com.alibaba.fastjson.JSON;
import com.miaotech.api.dto.UserInfoDTO;
import com.miaotech.common.converter.GeneralConvertor;
import com.miaotech.common.utils.TimeUtils;
import com.miaotech.dubbo.BaseTest;
import com.miaotech.dubbo.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class GeneralConvertorTest extends BaseTest {

    User user = null;

    @Autowired
    GeneralConvertor convertor;

    /**
     * 模拟从数据库中查出user对象
     */

    @BeforeEach
    public void setUp() {
        user  = new User(1, "hello", "", "15899000000",
                "陈hello", TimeUtils.yyyyMMdd("2000-10-17"), "test@163.com", new Date());
    }

    /**
     * 模拟把user对象转换成UserRoleDto对象
     */
    @Test
    public void userConverterTest() {
        UserInfoDTO userInfoDTO = convertor.convertor(user, UserInfoDTO.class);
        System.out.println(JSON.toJSONString(userInfoDTO));
        Assertions.assertThat(userInfoDTO.getUserId()).isEqualTo(user.getId());
    }
}
