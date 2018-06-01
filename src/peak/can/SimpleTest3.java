package peak.can;

import peak.can.basic.*;

public class SimpleTest3 {


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

        //Local variables
        TPCANMsg canMessage = null;
        TPCANTimestamp rcvTime = null;
        TPCANStatus ret;
        //Variables
        String msgIDStr = "";
        String msgLength = "";
        String msgType = "";
        String msgData = "";
        String blockData = "";
        String msgCount = "";
        String msgRcvTime = "";
        Object[] msgTableObect = null;

        // Crate New instance of PCANBasic
        pcanBasic = new PCANBasic();

        // JNI Initialization
        pcanBasic.initializeAPI();

        TPCANHandle pcan_handle = TPCANHandle.PCAN_PCIBUS1;
        TPCANType pcan_type = TPCANType.PCAN_TYPE_NONE;
        TPCANBaudrate pcan_baudrate = TPCANBaudrate.PCAN_BAUD_100K;

        //channelitem = new ChannelItem(TPCANHandle.PCAN_USBBUS1, TPCANType.PCAN_TYPE_ISA_SJA);
        channelitem = new ChannelItem(pcan_handle, pcan_type);
        channelitem.setWorking(true);

        TPCANStatus res;

        //res = pcanBasic.Initialize(pcan_handle, pcan_baudrate, pcan_type);
        res = pcanBasic.Initialize(pcan_handle, pcan_baudrate, pcan_type, 0, (short) 0);
        //        res = pcanBasic.Initialize(pcan_handle, pcan_baudrate, pcan_type);
        if (res == TPCANStatus.PCAN_ERROR_OK) {
            System.out.println(channelitem.getHandle().toString() + " Successfully initialized");
        }

        canMessage = new TPCANMsg();
        TPCANTimestamp tpcan_timestamp = new TPCANTimestamp();
        tpcan_timestamp.setMicros((short) 3);
        tpcan_timestamp.setMillis((short) 2);
        tpcan_timestamp.setMillis_overflow((short) 1);


        res = pcanBasic.FilterMessages(pcan_handle, 0x000, 0x700, TPCANMode.PCAN_MODE_STANDARD);
        // We execute the "Read" function of the PCANBasic
        ret = pcanBasic.Read(pcan_handle, canMessage, tpcan_timestamp);

        //Process result
        if (ret == TPCANStatus.PCAN_ERROR_OK) {

            System.out.println(channelitem.getHandle().toString() + " Successfully Read");

            //Critical Area: dataRowCollection is used in multiple threads
            //                    synchronized (MainJFrame.token)
            //                    {
            msgType = String.valueOf(canMessage.getType());
            msgLength = String.valueOf(canMessage.getLength());
            msgIDStr = Integer.toHexString(canMessage.getID());

            //Message Data
            for (int dataIndex = 0; dataIndex < canMessage.getLength(); dataIndex++) {
                msgData = msgData + canMessage.getData()[dataIndex] + " ";
            }

            //Message Count
            msgCount = String.valueOf(1);
            msgRcvTime = (rcvTime != null) ? String.valueOf(rcvTime.getMillis()) + "." + String.valueOf(rcvTime.getMicros()) : null;

            //Construct JTable Object
            msgTableObect = new Object[]{msgType, msgIDStr, msgLength, msgData, msgCount, msgRcvTime};
            //     model.addRow(msgTableObect);

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + msgType + " " + msgIDStr + " " + msgLength + " " + msgData + " " + msgCount + " " + msgRcvTime);
        }

        res = pcanBasic.Uninitialize(channelitem.getHandle());
        if (res == TPCANStatus.PCAN_ERROR_OK) {
            System.out.println(channelitem.getHandle().toString() + " Successfully unitialized");
        }

    }
}