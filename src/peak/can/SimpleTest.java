package peak.can;

import peak.can.basic.*;

import java.util.HashMap;

public class SimpleTest {

    // PCANBasic instance
    private static PCANBasic pcanBasic = null;

    private static TPCANMsg canMessage = null;

    private static TPCANTimestamp rcvTime = null;

    private static TPCANStatus ret;

    // Map to store received messages
    private static HashMap<Integer, TableDataRow> receivedData = new HashMap<Integer, TableDataRow>();

    volatile static ChannelItem channelitem = null;

    // Thread to read CAN messages
    static CANReadThread canReadThread;


    public static void main(String[] args) {

        // Crate New instance of PCANBasic
        pcanBasic = new PCANBasic();

        // JNI Initialization
        pcanBasic.initializeAPI();

        TPCANHandle pcan_handle = TPCANHandle.PCAN_PCIBUS1;
        TPCANType pcan_type = TPCANType.PCAN_TYPE_NONE;
        TPCANBaudrate pcan_baudrate = TPCANBaudrate.PCAN_BAUD_100K;

        TPCANStatus res;

        try {
            res = pcanBasic.Initialize(pcan_handle, TPCANBaudrate.PCAN_BAUD_100K, TPCANType.PCAN_TYPE_NONE, 0, (short) 0);

            res = pcanBasic.FilterMessages(pcan_handle, 0x000, 0x700, TPCANMode.PCAN_MODE_STANDARD);
            if (res == TPCANStatus.PCAN_ERROR_OK) {
//                {
//                    TPCANMsg msg = new TPCANMsg();
//                    msg.setID(0x700);
//                    msg.setData(new byte[]{0x02, 0x3E, 0x00}, (byte) 3);
//                    msg.setType(TPCANMessageType.PCAN_MESSAGE_STANDARD);
//                    res = pcanBasic.Write(pcan_handle, msg);
//                    System.out.println("Write: " + res);
//                }

                for (int i = 0; i < 1000; i++) {
                    {
                        TPCANMsg msg = new TPCANMsg();
                        TPCANTimestamp timestamp = new TPCANTimestamp();
                        msg = new TPCANMsg();
                        res = pcanBasic.Read(pcan_handle, msg, timestamp);
                        System.out.println("Read: " + res);
                        System.out.println(convertToString(msg));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                    }
                }

            } else {
                System.out.println("Error by reading the data");
                new RuntimeException("Error by reading the data");
            }
        } finally {
            res = pcanBasic.Uninitialize(pcan_handle);
        }
    }

    private static String convertToString(TPCANMsg msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID: %02X / ", msg.getID()));
        for (byte b : msg.getData()) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}
