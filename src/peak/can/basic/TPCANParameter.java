package peak.can.basic;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Parameter definition.
 *
 * @author Jonathan Urban/Uwe Wilhelm/Fabrice Vergnaud
 * @version 1.10
 * @LastChange $Date: 2016-05-13 14:55:44 +0200 (Fr, 13 Mai 2016) $
 * @Copyright (C) 1999-2009 PEAK-System Technik GmbH, Darmstadt more Info at
 * http://www.peak-system.com
 */
public enum TPCANParameter {

    /**
     * PCAN-USB device number parameter
     */
    PCAN_DEVICE_NUMBER(0x01),
    /**
     * PCAN-PC Card 5-Volt power parameter
     */
    PCAN_5VOLTS_POWER(0x02),
    /**
     * PCAN receive event handler parameter
     */
    PCAN_RECEIVE_EVENT(0x03),
    /**
     * PCAN message filter parameter
     */
    PCAN_MESSAGE_FILTER(0x04),
    /**
     * PCAN-Basic API version parameter
     */
    PCAN_API_VERSION(0x05),
    /**
     * PCAN device channel version parameter
     */
    PCAN_CHANNEL_VERSION(0x06),
    /**
     * PCAN Reset-On-Busoff parameter
     */
    PCAN_BUSOFF_AUTORESET(0x07),
    /**
     * PCAN Listen-Only parameter
     */
    PCAN_LISTEN_ONLY(0x08),
    /**
     * Directory path for trace files
     */
    PCAN_LOG_LOCATION(0x09),
    /**
     * Debug-Trace activation status
     */
    PCAN_LOG_STATUS(0x0A),
    /**
     * Configuration of the debugged information (LOG_FUNCTION_***)
     */
    PCAN_LOG_CONFIGURE(0x0B),
    /**
     * Custom insertion of text into the log file
     */
    PCAN_LOG_TEXT(0x0C),
    /**
     * Availability status of a PCAN-Channel
     */
    PCAN_CHANNEL_CONDITION(0x0D),
    /**
     * PCAN hardware name parameter
     */
    PCAN_HARDWARE_NAME(0x0E),
    /**
     * Message reception status of a PCAN-Channel
     */
    PCAN_RECEIVE_STATUS(0x0F),
    /**
     * CAN-Controller number of a PCAN-Channel
     */
    PCAN_CONTROLLER_NUMBER(0x10),
    /**
     * Directory path for PCAN trace files
     */
    PCAN_TRACE_LOCATION(0x11),
    /**
     * CAN tracing activation status
     */
    PCAN_TRACE_STATUS(0x12),
    /**
     * Configuration of the maximum file size of a CAN trace
     */
    PCAN_TRACE_SIZE(0x13),
    /**
     * Configuration of the trace file storing mode (TRACE_FILE_***)
     */
    PCAN_TRACE_CONFIGURE(0x14),
    /**
     * Physical identification of a USB based PCAN-Channel by blinking its
     * associated LED
     */
    PCAN_CHANNEL_IDENTIFYING(0x15),
    /**
     * Capabilities of a PCAN device (FEATURE_***)
     */
    PCAN_CHANNEL_FEATURES(0x16),
    /**
     * Using of an existing bit rate (PCAN-View connected to a channel)
     */
    PCAN_BITRATE_ADAPTING(0x17),
    /**
     * Configured Bit rate as Btr0Btr1 value
     */
    PCAN_BITRATE_INFO(0x18),
    /**
     * Configured Bit rate as TPCANBitrateFD string
     */
    PCAN_BITRATE_INFO_FD(0x19),
    /**
     * Configured nominal CAN Bus speed as Bits per seconds
     */
    PCAN_BUSSPEED_NOMINAL(0x1A),
    /**
     * Configured CAN data speed as Bits per seconds
     */
    PCAN_BUSSPEED_DATA(0x1B),
    /**
     * Remote address of a LAN channel as string in IPv4 format
     */
    PCAN_IP_ADDRESS(0x1C);


    TPCANParameter(int value) {
        this.value = value;
    }

    /**
     * The identifier of the CAN parameter
     *
     * @return Identifier of the CAN parameter
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the list of PCAN Parameters which are customizable as array
     *
     * @return the list of PCAN Parameters which are customizable as array
     */
    @Nonnull
    public static TPCANParameter[] customizableParameters() {
        final List<TPCANParameter> result = new ArrayList<TPCANParameter>();
        result.add(TPCANParameter.PCAN_DEVICE_NUMBER);
        result.add(TPCANParameter.PCAN_5VOLTS_POWER);
        result.add(TPCANParameter.PCAN_API_VERSION);
        result.add(TPCANParameter.PCAN_CHANNEL_VERSION);
        result.add(TPCANParameter.PCAN_BUSOFF_AUTORESET);
        result.add(TPCANParameter.PCAN_LISTEN_ONLY);
        result.add(TPCANParameter.PCAN_LOG_LOCATION);
        result.add(TPCANParameter.PCAN_LOG_STATUS);
        result.add(TPCANParameter.PCAN_LOG_CONFIGURE);
        result.add(TPCANParameter.PCAN_CHANNEL_CONDITION);
        result.add(TPCANParameter.PCAN_HARDWARE_NAME);
        result.add(TPCANParameter.PCAN_RECEIVE_STATUS);
        result.add(TPCANParameter.PCAN_CONTROLLER_NUMBER);
        result.add(TPCANParameter.PCAN_TRACE_LOCATION);
        result.add(TPCANParameter.PCAN_TRACE_STATUS);
        result.add(TPCANParameter.PCAN_TRACE_SIZE);
        result.add(TPCANParameter.PCAN_TRACE_CONFIGURE);
        result.add(TPCANParameter.PCAN_CHANNEL_IDENTIFYING);
        result.add(TPCANParameter.PCAN_CHANNEL_FEATURES);
        result.add(TPCANParameter.PCAN_BITRATE_ADAPTING);
        result.add(TPCANParameter.PCAN_BITRATE_INFO);
        result.add(TPCANParameter.PCAN_BITRATE_INFO_FD);

        result.add(TPCANParameter.PCAN_BUSSPEED_NOMINAL);
        result.add(TPCANParameter.PCAN_BUSSPEED_DATA);
        result.add(TPCANParameter.PCAN_IP_ADDRESS);

        return result.toArray(new TPCANParameter[result.size()]);
    }

    /**
     * Returns a description for the given TPCANParameter
     *
     * @param param TPCANParameter for which we need description
     * @return a string description for the given TPCANParameter
     */
    @Nonnull
    public static String getParameterDescription(@Nonnull TPCANParameter param) {
        switch (param) {
            case PCAN_DEVICE_NUMBER:
                return "This parameter is used on PCAN-USB hardware to distinguish between 2 (or more) of them on the same computer. This value is persistent, i.e. the identifier will not be lost after disconnecting and connecting again the hardware.";
            case PCAN_5VOLTS_POWER:
                return "This parameter is used on PCAN-PC Card hardware for switching the external 5V on the D-Sub connector of the PC Card. This is useful when connecting external bus converter modules to the card (AU5790 / TJA1954)).";
            case PCAN_API_VERSION:
                return "This parameter is used to get information about the PCAN-Basic API implementation version.";
            case PCAN_CHANNEL_VERSION:
                return "This parameter is used to get version information about the Driver of a PCAN Channel.";
            case PCAN_BUSOFF_AUTORESET:
                return "This parameter instructs the PCAN driver to reset automatically the CAN controller of a PCAN channel when a bus-off state is detected. Since no communication is possible on a bus-off state, it is useful to let the driver to catch this event automatically and reset the controller, avoiding extra handling of this problem in an end application.";
            case PCAN_LISTEN_ONLY:
                return "This parameter allows the user to set a CAN hardware in Listen-Only mode. When this mode is set, the CAN controller doens't take part on active events (eg. transmit CAN messages) but stays in a passive mode (CAN monitor), in which it can analyse the traffic on the CAN bus used by a PCAN channel. See also the Philips Data Sheet \"SJA1000 Stand-alone CAN controller\".";
            case PCAN_LOG_LOCATION:
                return "This value is used to set the folder location on a computer for the Log-File generated by the PCAN-Basic API, within a debug session. Setting this value starts recording debug information automatically. If a debug session is running (a log file is being written), PCAN_LOG_LOCATION instructs the API to close the current log file and to start the process again with the new folder information. Note that the name of the log file cannot be specified, this name is fixed as PCANBasic.log.";
            case PCAN_LOG_STATUS:
                return "This value is used to control the activity status of a debug session within the PCAN-Basic API. If the log status is set to ON without having set a location for the log file or without having configured the information to be traced, then the session process will start with the default values.";
            case PCAN_LOG_CONFIGURE:
                return "This value is used to configure the debug information to be included in the log file generated in a debug session within the PCAN-Basic API.";
            case PCAN_CHANNEL_CONDITION:
                return "This parameter is used to check and detect available PCAN hardware on a computer, even before trying to connect any of them. This is useful when an application wants the user to select which hardware should be using in a communication session.";
            case PCAN_HARDWARE_NAME:
                return "This parameter is to read the Name of the Hardware.";
            case PCAN_RECEIVE_STATUS:
                return "This Parameter is to get the reception status of a PCAN-Channel.";
            case PCAN_CONTROLLER_NUMBER:
                return "This Parameter is to get CAN-Controller number of a PCAN-Channel. Only usefull with 2 Channel HW.";
            case PCAN_TRACE_LOCATION:
                return "This Parameter is used to configure the Trace File Directory.";
            case PCAN_TRACE_STATUS:
                return "This Parameter is used to control the activity of the Tracer status.";
            case PCAN_TRACE_SIZE:
                return "This Parameter is used to configure the maximum file size of a CAN trace.";
            case PCAN_TRACE_CONFIGURE:
                return "This Parameter is used to configure the trace file storing mode (TRACE_FILE_***).";
            case PCAN_CHANNEL_IDENTIFYING:
                return "This Parameter is used to Physically identify a USB based PCAN-Channel by blinking its associated LED.";
            case PCAN_CHANNEL_FEATURES:
                return "This Parameter is used to get the capabilities of a PCAN device.";
            case PCAN_BITRATE_ADAPTING:
                return "This Parameter is used to force an initialization process to succeed, even if the PCAN-Channel is being used by a PCAN-View with a different or unknown bit rate.";
            case PCAN_BITRATE_INFO:
                return "This Parameter is used to read the currently configured communication speed of a PCAN Channel connected as standard CAN.";
            case PCAN_BITRATE_INFO_FD:
                return "This Parameter is used to read the currently configured communication speed, as a parameterized string value (FD Bit rate String), of a PCAN Channel connected as CAN FD.";
            case PCAN_BUSSPEED_NOMINAL:
                return "This Parameter is used to read the configured nominal CAN Bus speed as Bits per seconds.";
            case PCAN_BUSSPEED_DATA:
                return "This Parameter is used to read configured CAN data speed as Bits per seconds.";
            case PCAN_IP_ADDRESS:
                return "This Parameter is used to read the remote address of a LAN channel as string in IPv4 format.";
            case PCAN_LOG_TEXT:
                return "This Parameter is used to insert custom information in the log file generated in a debug session within the PCAN-Basic API.";
            case PCAN_MESSAGE_FILTER:
                return "This parameter is used to let the PCAN driver notify an application when CAN messages are placed in its receive queue.";
            case PCAN_RECEIVE_EVENT:
                return "This parameter allows the user to easy configure the message filter of a PCAN channel. With it is possible to fully open or complete close the filter.";
            default:
                return "";
        }
    }

    private final int value;
}
