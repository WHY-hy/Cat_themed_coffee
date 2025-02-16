package com.Cat_themed_coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author why
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Cat_themed_coffeeApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Cat_themed_coffeeApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  猫咪主题咖啡服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "    _____ __             __           __   _____                                ____      ____ \n" +
                "   / ___// /_____ ______/ /____  ____/ /  / ___/__  _______________  __________/ __/_  __/ / /_  __ \n" +
                "   \\__ \\/ __/ __ `/ ___/ __/ _ \\/ __  /   \\__ \\/ / / / ___/ ___/ _ \\/ ___/ ___/ /_/ / / / / / \n" +
                "  ___/ / /_/ /_/ / /  / /_/  __/ /_/ /   ___/ / /_/ / /__/ /__/  __(__  |__  ) __/ /_/ / / / /_/ / \n" +
                " /____/\\__/\\__,_/_/   \\__/\\___/\\__,_/   /____/\\__,_/\\___/\\___/\\___/____/____/_/  \\__,_/_/_/\\__, / \n" +
                "                                                                                          /____/       \n" );
    }
}
