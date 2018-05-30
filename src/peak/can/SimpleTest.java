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

        /*TPCANHandle pcan_handle = TPCANHandle.PCAN_PCIBUS2;
        TPCANType pcan_type = TPCANType.PCAN_TYPE_NONE;
        TPCANBaudrate pcan_baudrate = TPCANBaudrate.PCAN_BAUD_100K;*/

        TPCANHandle pcan_handle = TPCANHandle.PCAN_PCIBUS1;
        TPCANType pcan_type = TPCANType.PCAN_TYPE_NONE;
        TPCANBaudrate pcan_baudrate = TPCANBaudrate.PCAN_BAUD_1M;

        //        for (TPCANType pcan_type : TPCANType.values()) {
        //        for (TPCANBaudrate pcan_baudrate : TPCANBaudrate.values()) {
        //        for (TPCANHandle pcan_handle : TPCANHandle.values()) {

        //channelitem = new ChannelItem(TPCANHandle.PCAN_USBBUS1, TPCANType.PCAN_TYPE_ISA_SJA);
        channelitem = new ChannelItem(pcan_handle, pcan_type);
        channelitem.setWorking(true);

        TPCANStatus res;
        //         res = pcanBasic.Initialize(TPCANHandle.PCAN_USBBUS1, pcan_baudrate, pcan_type, 100, (short) 3);
        res = pcanBasic.Initialize(pcan_handle, pcan_baudrate, pcan_type, 0, (short) 0);

        if (res == TPCANStatus.PCAN_ERROR_OK) {

            System.out.println("NO NO NO NO Error by reading the data \n TPCANType =" + pcan_type +
                    "\n TPCANBaudrate =" + pcan_baudrate + "\n TPCANHandle =" + pcan_handle + "\n" +
                    channelitem.getHandle().toString() + " Successfully initialized");

            TPCANMsg msg = new TPCANMsg();
            pcanBasic.Read(channelitem.getHandle(), msg, null);
            pcanBasic.Uninitialize(channelitem.getHandle());

//            // Create New CANReadThread with default values
//            canReadThread = new CANReadThread(pcanBasic, channelitem, receivedData);
//            // Start Timer Thread to read CAN Messages
//            canReadThread.start();
//
//            //Process result
//            for (Map.Entry<Integer, TableDataRow> entry : receivedData.entrySet()) {
//                System.out.println(entry.getKey() + "/*** JAWHAR ***/" + entry.getValue());
//            }
//            pcanBasic.Uninitialize(channelitem.getHandle());

        } else {
            System.out.println("Error by reading the data");
            new RuntimeException("Error by reading the data");
        }

        //        }
        //        }
        //        }
    }
}
