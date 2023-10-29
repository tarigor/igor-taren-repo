package com.senla.webtask.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        if (requestURI.endsWith("/")) {
            handleRequest("Start Page", "click -> <a href=\"/hello\">/hello</a>", req, resp);
        } else if (requestURI.endsWith("/hello")) {
            handleRequest("Hello World Page", "Hello World!", req, resp);
        }
        handleRequest("Error Page", "oops...", req, resp);
    }

    private void handleRequest(String pageName, String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", pageName);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(req, resp);
    }
}
