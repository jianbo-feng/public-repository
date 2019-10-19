# 项目说明
## 打包部署到tomcat8(不注释会导致重复的ServerEndpointExporter实例)说明
```
@Configuration
public class WebSocketConfig {

    // 打包war/jar放到tomcat中时需要注释掉
    /*
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    */
}
```
