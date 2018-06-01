package peak.can;

import peak.can.basic.*;

import java.util.HashMap;

public class SimpleTest2 {

    // PCANBasic instance
    private static PCANBasic pcanBasic = null;

    private static TPCANMsg canMessage = null;

    private static TPCANTimestamp rcvTime = null;

    private static TPCANStatus ret;

    volatile static ChannelItem channelitem = null;

    // Thread to read CAN messages
    static CANReadThread canReadThread;
    static CANReceiveThreadCustomized canReceiveThread;


    public static void main(String[] args) {
        // Crate New instance of PCANBasic
        pcanBasic = new PCANBasic();

        // JNI Initialization
        pcanBasic.initializeAPI();

        TPCANHandle pcan_handle = TPCANHandle.PCAN_PCIBUS2;
        TPCANType pcan_type = TPCANType.PCAN_TYPE_NONE;
        TPCANBaudrate pcan_baudrate = TPCANBaudrate.PCAN_BAUD_100K;

        channelitem = new ChannelItem(pcan_handle, pcan_type);
        channelitem.setWorking(true);

        TPCANStatus res;


        res = pcanBasic.Initialize(pcan_handle, pcan_baudrate, pcan_type, 0, (short) 0);
        res = pcanBasic.FilterMessages(pcan_handle, 0x000, 0x700, TPCANMode.PCAN_MODE_STANDARD);

        if (res == TPCANStatus.PCAN_ERROR_OK) {

            // Map to store received messages
            HashMap<Integer, TableDataRow> receivedData = new HashMap<Integer, TableDataRow>();

            res = pcanBasic.FilterMessages(pcan_handle, 0x000, 0x700, TPCANMode.PCAN_MODE_STANDARD);
            // Create New CANReadThread with default values
            canReadThread = new CANReadThread(pcanBasic, channelitem, receivedData);
            canReadThread.setReadTimeStamp(true);

            // Start Timer Thread to read CAN Messages
            canReadThread.start();

            /*synchronized (canReadThread) {
                try {
                    System.out.println("Waiting for canReadThread to complete...");
                    canReadThread.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

           /* RcvEventDispatcher.setListener(canReadThread);
            if (channelitem.getWorking()) {
                TPCANStatus status = pcanBasic.SetRcvEvent(channelitem.getHandle());
                System.out.println(status);
            }
            //Process result
            for (Map.Entry<Integer, TableDataRow> entry : receivedData.entrySet()) {
                System.out.println(" key " + entry.getKey() + " value " + entry.getValue());
            }*/


            /*res = pcanBasic.Uninitialize(channelitem.getHandle());
            if (res == TPCANStatus.PCAN_ERROR_OK) {
                System.out.println(channelitem.getHandle().toString() + " Successfully unitialized");
            }*/


           /* // Map to store received messages
            HashMap<Integer, TableDataRow> receivedData = new HashMap<Integer, TableDataRow>();
            canReceiveThread = new CANReceiveThreadCustomized(pcanBasic, channelitem, receivedData);
            canReceiveThread.setReadTimeStamp(true);
            canReceiveThread.start();

            res = pcanBasic.Uninitialize(channelitem.getHandle());
            if (res == TPCANStatus.PCAN_ERROR_OK) {
                System.out.println(channelitem.getHandle().toString() + " Successfully unitialized");
            }*/

        } else {
            System.out.println("Error by reading the data");
        }
    }
}



     /*int messageID = 0;
            while (messageID < 100) {
                TPCANMsg msg = new TPCANMsg();
                pcanBasic.Read(channelitem.getHandle(), msg, null);
                if (msg != null) {
                    String value = "";

                    for (int i = 0; i < msg.getData().length; i++) {
                        value = value + msg.getData()[i];
                    }

                    System.out.println("key = " + msg.getID() + " value = " + value);
                    *//*TableDataRow dataRow = new TableDataRow();
                    dataRow.setMessage(msg);
                    dataRow.setCounter(1);
                    // Collection to store readed CAN Messages
                    HashMap<Integer, TableDataRow> dataRowCollection = new HashMap<>();
                    dataRowCollection.put(messageID, dataRow);
                    System.out.println("key = " + dataRow.getMsgId() + " value = " + dataRow.getMsgData());*//*
                    messageID++;
                } else {
                    System.out.println("message is null");
                }
            }
            pcanBasic.Uninitialize(channelitem.getHandle());
*/