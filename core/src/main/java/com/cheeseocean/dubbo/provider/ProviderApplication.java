/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheeseocean.dubbo.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class ProviderApplication {

    public static void main(String[] args) throws Exception {
        System.setProperty("dubbo.application.logger", "slf4j");
        System.setProperty("native", "true");
//        if (isClassic(args)) {
//            startWithExport();
//        } else {
        startWithBootstrap();
//        }
        System.in.read();
    }

    private static boolean isClassic(String[] args) {
        return args.length > 0 && "classic".equalsIgnoreCase(args[0]);
    }

    private static void startWithBootstrap() {
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        service.setInterface(DemoService.class);
        service.setOnconnect("onConnect");
        service.setRef(new DemoServiceImpl());
        ServiceConfig<HelloServiceImpl> helloService = new ServiceConfig<>();
        helloService.setInterface(HelloService.class);
        helloService.setRef(new HelloServiceImpl());

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();

        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-api-provider");
        applicationConfig.setQosEnable(false);
        applicationConfig.setCompiler("jdk");
        Map<String, String> m = new HashMap<>(1);
        m.put("proxy", "jdk");
        applicationConfig.setParameters(m);

        bootstrap.application(applicationConfig)
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1))
                .service(service)
                .start()
                .await();
    }

    private static void startWithExport() throws InterruptedException {
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());

        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-api-provider");
        applicationConfig.setQosEnable(false);
        applicationConfig.setCompiler("jdk");

        Map<String, String> m = new HashMap<>(1);
        m.put("proxy", "jdk");
        applicationConfig.setParameters(m);

        service.setApplication(applicationConfig);
        service.setRegistry(new RegistryConfig("nacos://127.0.0.1:8848"));
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
