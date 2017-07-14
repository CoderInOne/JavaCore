package xunshan.features.proxy.aop;

import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    private String name;
    private String url;

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
