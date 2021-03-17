package com.BoostingWebsite.utils;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    protected ApplicationSession applicationSession;
}
