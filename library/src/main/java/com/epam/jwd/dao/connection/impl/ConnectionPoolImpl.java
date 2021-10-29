package com.epam.jwd.dao.connection.impl;

import com.epam.jwd.dao.connection.api.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionPoolImpl implements ConnectionPool {
    private final String DB_PROPERTIES = "db.properties";
    private final String DB_URL = "url";
    private final String DB_DRIVER = "driver";
    private final int POOL_SIZE = 5;

    // TODO: to know about queue, which queue to use
    private final BlockingQueue<ProxyConnection> freeConnection = new LinkedBlockingQueue<>();
    private final BlockingQueue<ProxyConnection> busyConnection = new LinkedBlockingQueue<>();

    // TODO: need synchronized or lock
    private volatile static ConnectionPoolImpl instance;
    private boolean initialized = false;

    private ConnectionPoolImpl() {}

    public static ConnectionPoolImpl getInstance(){
        if (instance == null){
            synchronized (ConnectionPoolImpl.class) {
                if (instance == null) {
                    instance = new ConnectionPoolImpl();
                    instance.init();
                }
            }
        }
        return instance;
    }

    @Override
    public void init() {
        if (!initialized) {
            initializePool();
            initialized = true;
        }
    }

    @Override
    public void destroy() {
        if (initialized) {
            closeConnections(freeConnection);
            freeConnection.clear();
            closeConnections(busyConnection);
            busyConnection.clear();
            initialized = false;
        }
    }

    @Override
    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnection.take();
            busyConnection.add(connection);
        } catch (InterruptedException e) {
            // TODO: add logger
        }
        return connection;
    }

    @Override
    public void returnConnection(Connection connection) {
        if (busyConnection.remove(connection)) {
            freeConnection.add((ProxyConnection) connection);
        } else {
            // TODO: add logger
        }
    }

    private void initializePool() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(DB_PROPERTIES));
            String dbUrl = properties.getProperty(DB_URL);
            String dbDriver = properties.getProperty(DB_DRIVER);
            Class.forName(dbDriver);

            for (int i = 0; i < POOL_SIZE; i++) {
                createConnection(dbUrl, properties);
            }
            // TODO: ask about many exception
        } catch (IOException | ClassNotFoundException | SQLException e) {
            //TODO: add logger and exception
        }
    }

    private void createConnection(String dbUrl, Properties properties) throws SQLException{
        Connection connection = DriverManager.getConnection(dbUrl, properties);
        ProxyConnection proxyConnection = new ProxyConnection(connection, this);
        freeConnection.add(proxyConnection);
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        connections.forEach(this::closeConnection);
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.reallyClose();
        } catch (SQLException e) {
            // TODO: add logger
        }
    }

}
