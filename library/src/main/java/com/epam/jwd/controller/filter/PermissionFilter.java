package com.epam.jwd.controller.filter;


import com.epam.jwd.controller.command.CommandsName;
import com.epam.jwd.dao.entity.user.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import static com.epam.jwd.controller.ApplicationController.COMMAND_PARAM;
import static com.epam.jwd.controller.command.impl.LoginCommand.USER_ROLE_SESSION_ATTRIB_NAME;
import static com.epam.jwd.dao.entity.user.UserRole.GUEST;

@WebFilter(urlPatterns = "/*")
public class PermissionFilter implements Filter {
    private static final String ERROR_PAGE_LOCATION = "/library?command=SHOW_ERROR_PAGE";
    private final Map<UserRole, Set<CommandsName>> commandsByRole;

    public PermissionFilter() {
        this.commandsByRole = new EnumMap<>(UserRole.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final CommandsName command = CommandsName.of(req.getParameter(COMMAND_PARAM));
        final HttpSession session = req.getSession(false);
        UserRole currentRole = extractRoleFromSession(session);
        final Set<CommandsName> allowedCommands = commandsByRole.get(currentRole);
        if (allowedCommands.contains(command)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(ERROR_PAGE_LOCATION);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        for (CommandsName command : CommandsName.values()) {
            for (UserRole allowedRole : command.getAllowedRoles()) {
                Set<CommandsName> commands = commandsByRole.computeIfAbsent(allowedRole,
                        k -> EnumSet.noneOf(CommandsName.class));
                commands.add(command);
            }
        }
    }

    private UserRole extractRoleFromSession(HttpSession session) {
        return session != null && session.getAttribute(USER_ROLE_SESSION_ATTRIB_NAME) != null
                ? (UserRole) session.getAttribute(USER_ROLE_SESSION_ATTRIB_NAME)
                : GUEST;
    }
}
