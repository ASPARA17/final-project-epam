package com.epam.jwd.controller;

import com.epam.jwd.controller.command.api.Command;
import com.epam.jwd.controller.command.api.CommandRequest;
import com.epam.jwd.controller.command.api.CommandResponse;
import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/controller")
public class ApplicationController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ApplicationController.class);

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public static final String COMMAND_PARAM = "command";
    private static final String INIT_ERROR_MESSAGE = "Can't initialize the connectionPool";
    private static final String DESTROY_ERROR_MESSAGE = "Can't shutdown the connectionPool";

    public ApplicationController() { super();}

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
        final Command command = Command.withName(commandName);
        final CommandResponse response = command.execute(new CommandRequest() {
            private static final String REFERER = "referer";
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

            @Override
            public String getHeader() {
                return req.getHeader(REFERER);
            }

            @Override
            public String getContextPath() {
                return req.getContextPath();
            }
        });

        if (response.isRedirect()) {
            resp.sendRedirect(response.getPath());
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(response.getPath());
            dispatcher.forward(req, resp);
        }
    }


    @Override
    public void init() {
        try {
            connectionPool.init();
        } catch (DaoException e) {
            log.error(INIT_ERROR_MESSAGE, e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            connectionPool.destroy();
        } catch (DaoException e) {
            log.error(DESTROY_ERROR_MESSAGE, e);
        }
    }
}
