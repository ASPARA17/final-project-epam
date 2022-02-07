package com.epam.jwd.dao.connection.impl;

import com.epam.jwd.dao.connection.api.ConnectionPool;
import com.epam.jwd.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger log = LogManager.getLogger(ConnectionPoolImpl.class);
    private final String DB_PROPERTIES = "db.properties";
    private final String DB_URL = "url";
    private final String DB_DRIVER = "driver";
    private final int POOL_SIZE = 5;

    String INTERRUPT_EXCEPTION = "Current thread was interrupted";
    String CONNECTION_EXCEPTION = "Connection failed";

    private static ConnectionPool instance = new ConnectionPoolImpl();

    private final BlockingQueue<ProxyConnection> freeConnection;
    private final BlockingQueue<ProxyConnection> busyConnection;
    private boolean initialized = false;

    private ConnectionPoolImpl() {
        this.freeConnection = new ArrayBlockingQueue<>(POOL_SIZE);
        this.busyConnection = new ArrayBlockingQueue<>(POOL_SIZE);
    }

    public static ConnectionPool getInstance(){
        synchronized (ConnectionPool.class) {
            if (instance == null) {
                instance = new ConnectionPoolImpl();
                return instance;
            }
        }
        return instance;
    }

    @Override
    public void init() throws DaoException {
        if (!initialized) {
            initializePool();
            initialized = true;
        }
    }

    @Override
    public void destroy() throws DaoException {
        closeConnections(freeConnection);
        closeConnections(busyConnection);
    }

    @Override
    public Connection takeConnection() {
        try {
            final ProxyConnection connection = freeConnection.take();
            busyConnection.put(connection);
            return connection;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(INTERRUPT_EXCEPTION + e);
        }
        return null;
    }

    @Override
    public void returnConnection(Connection connection) {
        try {
            freeConnection.put(busyConnection.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(INTERRUPT_EXCEPTION + e);
        }
    }

    private void initializePool() throws DaoException {
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

        } catch (IOException | ClassNotFoundException e) {
            log.error(CONNECTION_EXCEPTION + e);
            throw new DaoException(CONNECTION_EXCEPTION + e);
        }
    }

    private void createConnection(String dbUrl, Properties properties) throws DaoException{
        try {
            final Connection connection = DriverManager.getConnection(dbUrl, properties);
            final ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            freeConnection.put(proxyConnection);
            log.info("crete connection");
        } catch (SQLException e) {
            log.error(CONNECTION_EXCEPTION + e);
            throw new DaoException(CONNECTION_EXCEPTION + e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(INTERRUPT_EXCEPTION + e);
        }

    }

    private void closeConnections(Collection<ProxyConnection> connections) throws DaoException {
        for (ProxyConnection connection : connections) {
            closeConnection(connection);
        }
    }

    private void closeConnection(ProxyConnection connection) throws DaoException {
        try {
            connection.reallyClose();
        } catch (SQLException e) {
            log.error(CONNECTION_EXCEPTION + e);
            throw new DaoException(CONNECTION_EXCEPTION + e);
        }
    }

}
