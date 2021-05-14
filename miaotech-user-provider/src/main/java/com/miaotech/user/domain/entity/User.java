package com.miaotech.user.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.miaotech.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value="id",type= IdType.AUTO)
    @Mapping("userId")
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String nickname;

    private Date birth;

    private String email;

    //@TableField(fill = FieldFill.INSERT)
    private Date addtime;



}