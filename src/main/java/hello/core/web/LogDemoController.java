package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    // Proxy 사용
    @RequestMapping("log-demo")
    @ResponseBody   // 화면이 없이 반환할 때 사용
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }

    // Provider 사용
//    @RequestMapping("log-demo")
//    @ResponseBody   // 화면이 없이 반환할 때 사용
//    public String logDemo(HttpServletRequest request) {
//        String requestURL = request.getRequestURL().toString();
//        myLoggerProvider.getObject().setRequestUrl(requestURL);
//
//        myLoggerProvider.getObject().log("controller test");
//        logDemoService.logic("testId");
//
//        return "OK";
//    }

}
