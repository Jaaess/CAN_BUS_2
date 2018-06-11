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

        TPCANHandle pcan_handle = TPCANHandle.PCAN_PCIBUS2;
        TPCANType pcan_type = TPCANType.PCAN_TYPE_NONE;
        TPCANBaudrate pcan_baudrate = TPCANBaudrate.PCAN_BAUD_100K;

        TPCANStatus res;

        try {
            res = pcanBasic.Initialize(pcan_handle, TPCANBaudrate.PCAN_BAUD_100K, TPCANType.PCAN_TYPE_NONE, 0, (short) 0);
//            res = pcanBasic.FilterMessages(pcan_handle, 0x000, 0x7ff, TPCANMode.PCAN_MODE_STANDARD);
//            res = pcanBasic.FilterMessages(pcan_handle, 0x00000000, 0x7fffffff, TPCANMode.PCAN_MODE_STANDARD);

            if (res == TPCANStatus.PCAN_ERROR_OK) {

                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                }

                TPCANMsg msg = new TPCANMsg();
                TPCANTimestamp timestamp = new TPCANTimestamp();
//                res = pcanBasic.FilterMessages(pcan_handle, 0x3C3, 0x3C3, TPCANMode.PCAN_MODE_STANDARD);
                res = pcanBasic.FilterMessages(pcan_handle, 0x001, 0x003, TPCANMode.PCAN_MODE_STANDARD);
                res = pcanBasic.Read(pcan_handle, msg, timestamp);
                System.out.println("Read: " + res);
                System.out.println(convertToString(msg));

//                byte[] bytes = new byte[14];
//                ByteBuffer buffer = ByteBuffer.allocateDirect(50);
                TPCANParameterValue iBuffer = TPCANParameterValue.PCAN_FILTER_CLOSE;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                // close the filter
                pcanBasic.SetValue(pcan_handle, TPCANParameter.PCAN_MESSAGE_FILTER, iBuffer, iBuffer.toString().length());
                // Set the filter again
                res = pcanBasic.FilterMessages(pcan_handle, 0x400, 0x43F, TPCANMode.PCAN_MODE_STANDARD);
                TPCANMsg msg2 = new TPCANMsg();
                res = pcanBasic.Read(pcan_handle, msg2, timestamp);
                System.out.println("Read: " + res);
                System.out.println(convertToString(msg2));

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }

                // close the filter
                pcanBasic.SetValue(pcan_handle, TPCANParameter.PCAN_MESSAGE_FILTER, iBuffer, iBuffer.toString().length());
                // Set the filter again
                res = pcanBasic.FilterMessages(pcan_handle, 0x555, 0x555, TPCANMode.PCAN_MODE_STANDARD);
                TPCANMsg msg3 = new TPCANMsg();
                res = pcanBasic.Read(pcan_handle, msg3, timestamp);
                System.out.println("Read: " + res);
                System.out.println(convertToString(msg3));

//                for (int i = 0; i < 1000; i++) {
//                    TPCANMsg msg;
//                    TPCANTimestamp timestamp = new TPCANTimestamp();
//                    msg = new TPCANMsg();
//                    res = pcanBasic.Read(pcan_handle, msg, timestamp);
//                    System.out.println("Read: " + res);
//                    System.out.println(convertToString(msg));
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                    }
//                }

            } else {
                System.out.println("Error by reading the data");
//                new RuntimeException("Error by reading the data");
            }
        } finally {
            pcanBasic.Reset(pcan_handle);
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
