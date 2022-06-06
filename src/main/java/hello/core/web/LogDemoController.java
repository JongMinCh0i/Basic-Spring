package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    // log-demo 요청이 들어오면 아래(ResponseBody)와 같이 응답을 보낸다.
    @RequestMapping("log-demo")
    @ResponseBody
    // HttpServletRequest request : 자바에서 제공하는 표중 서블릿 규약이 있는데 그거에 의한 http Request정보를 받을 수 있다.
    // 고객 요청정보를 받을 수 있다.
    public String logDemo(HttpServletRequest request) {

        // 고객이 어떤 요청을 했는지 확인 가능
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        // Log를 찍게 하기 위함
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}