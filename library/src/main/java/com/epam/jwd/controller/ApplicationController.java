package com.epam.jwd.controller;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.CommandRequest;
import com.epam.jwd.controller.command.CommandResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class ApplicationController extends HttpServlet {
    private static final String COMMAND_PARAM = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandName = req.getParameter(COMMAND_PARAM);
        final Command command = Command.of(commandName);
        final CommandResponse response = command.execute(new CommandRequest() {
            @Override
            public HttpSession createSession() {
                return req.getSession(true);
            }

            @Override
            public Optional<HttpSession> getCurrentSession() {
                return Optional.ofNullable(req.getSession(false));
            }

            @Override
            public void invalidateCurrentSession() {
                final HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
            }

            @Override
            public String getParameter(String name) {
                return req.getParameter(name);
            }

            @Override
            public void setAttribute(String name, Object value) {
                req.setAttribute(name, value);
            }
        });

        if (response.isRedirect()) {
            resp.sendRedirect(response.getPath());
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(response.getPath());
            dispatcher.forward(req, resp);
        }
    }
}
