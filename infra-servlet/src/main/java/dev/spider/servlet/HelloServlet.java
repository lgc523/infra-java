package dev.spider.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author spider
 */
@WebServlet(name = "hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream is = req.getInputStream();
        System.out.println("data ready:" + is.isReady());
        System.out.println("data finished:" + is.isFinished());
        byte[] bytes = is.readAllBytes();
        String json = new String(bytes);
        System.out.println(json);
        Runtime runtime = Runtime.getRuntime();
        Thread fuck_please = new Thread(() -> System.out.println("fuck please"));
        runtime.addShutdownHook(fuck_please);
        PrintWriter writer = resp.getWriter();
        String location = resp.getHeader("Location");
        writer.println("hello");
//        runtime.exit(1);
//        runtime.halt(1);
    }
}
