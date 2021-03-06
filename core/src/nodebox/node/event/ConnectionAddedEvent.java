package nodebox.node.event;

import nodebox.node.Connection;
import nodebox.node.Network;
import nodebox.node.NodeEvent;

public class ConnectionAddedEvent extends NodeEvent {

    private Connection connection;

    public ConnectionAddedEvent(Network source, Connection connection) {
        super(source);
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public String toString() {
        return "ConnectionAddedEvent{" +
                "source=" + getSource() +
                "connection=" + connection +
                '}';
    }
}
