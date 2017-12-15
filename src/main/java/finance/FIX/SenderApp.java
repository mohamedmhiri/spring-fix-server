package FIX;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;

import java.util.Date;

public class SenderApp {

    public static void main(String[] args) throws ConfigError, InterruptedException, SessionNotFound {

        SessionSettings settings = new SessionSettings("sender.cfg");
        Application myApp = new FIXSender();
        FileStoreFactory fileStoreFactory = new FileStoreFactory(settings);
        ScreenLogFactory screenLogFactory = new ScreenLogFactory(settings);
        DefaultMessageFactory msgFactory = new DefaultMessageFactory();
        SocketInitiator initiator = new SocketInitiator(myApp, fileStoreFactory, settings,
                screenLogFactory, msgFactory);

        initiator.start();

        Thread.sleep(3000);

        // matching values from sender.cfg
        SessionID sessionID = new SessionID("FIX.4.4", "CLIENT1", "FixServer");
        NewOrderSingle order = new NewOrderSingle(new ClOrdID("DLF"), new Side(Side.BUY),
                new TransactTime(new Date()), new OrdType(OrdType.MARKET));

        order.set(new OrderQty(1000));
        order.set(new Price(40));
        order.set(new Symbol("BHP"));
        Session.sendToTarget(order, sessionID);

        Thread.sleep(60000);

        initiator.stop();
    }

    public static void execute(String orderID,String side, String orderType, int orderQty, double price, String symbol)throws ConfigError, InterruptedException, SessionNotFound {

        char sideCode = Side.UNDISCLOSED;
        char ordertypeCode = OrdType.MARKET;

        if(side == "buy") sideCode = Side.BUY;
        if(side == "sell") sideCode = Side.SELL;

        if(orderType == "market") ordertypeCode=OrdType.MARKET;
        if(orderType == "limit") ordertypeCode=OrdType.LIMIT;
        if(orderType == "stop") ordertypeCode=OrdType.STOP;
        if(orderType == "stoplimit") ordertypeCode=OrdType.STOP_LIMIT;



        SessionSettings settings = new SessionSettings("sender.cfg");
        Application myApp = new FIXSender();
        FileStoreFactory fileStoreFactory = new FileStoreFactory(settings);
        ScreenLogFactory screenLogFactory = new ScreenLogFactory(settings);
        DefaultMessageFactory msgFactory = new DefaultMessageFactory();
        SocketInitiator initiator = new SocketInitiator(myApp, fileStoreFactory, settings,
                screenLogFactory, msgFactory);

        initiator.start();

        Thread.sleep(1000);

        // matching values from sender.cfg
        SessionID sessionID = new SessionID("FIX.4.4", "CLIENT1", "FixServer");
        NewOrderSingle order = new NewOrderSingle(new ClOrdID(orderID), new Side(sideCode),
                new TransactTime(new Date()), new OrdType(ordertypeCode));

        order.set(new OrderQty(orderQty));
        order.set(new Price(price));
        order.set(new Symbol(symbol));
        Session.sendToTarget(order, sessionID);

        Thread.sleep(5000);

        initiator.stop();
    }
}