package com.kidskart.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nilesh on 10/05/15.
 */
public class Constants {



    public static String DOMAIN = "http://kidskartapp.a2zportals.com";
    public static String GET_DASHBOARDHTML_CODE_URL = DOMAIN+"/index.php/mobileapi/";
    public static String GET_MENUHTML_CODE_URL = DOMAIN+"/index.php/mobile-menu";

    public static final int TIMEOUT_SOCKET = (int) TimeUnit.MINUTES.toMillis(1);
	public static final int TIMEOUT_CONNECTION = (int) TimeUnit.MINUTES.toMillis(1);

}
