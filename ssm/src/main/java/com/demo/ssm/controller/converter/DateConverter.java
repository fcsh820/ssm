package com.demo.ssm.controller.converter;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/28.
 * 在Spring3 mvc中从前台到后台传递数据中如果包括日期类型的话，一般会报错: org.springframework.validation.BindException
 */
public class DateConverter implements WebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));
    }

}
