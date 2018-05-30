package peak.can.basic;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class is a gateway between the PCAN Light JNI and the application to dispatch the CAN Receive-Event.
 * <p>
 * RcvEventDispatcher contains a public static method dispatchRcvEvent which is called from the JNI to notify the Java
 * application when the handle of the Receive-Event detects a state change.
 *
 * @author Jonathan Urban/Uwe Wilhelm
 * @version 1.8
 * @LastChange $Date: 2016-05-13 14:55:44 +0200 (Fr, 13 Mai 2016) $
 * @Copyright (C) 1999-2014  PEAK-System Technik GmbH, Darmstadt
 * more Info at http://www.peak-system.com
 */
public class RcvEventDispatcher {
    @CheckForNull
    static private volatile IRcvEventProcessor listener;

    /**
     * Gets the Receive-Event processor
     *
     * @return a IRcvEventProcessor
     */
    @CheckForNull
    public static IRcvEventProcessor getListener() {
        return listener;
    }

    /**
     * Sets the Receive-Event processor
     *
     * @param listener a IRcvEventProcessor implementor
     */
    public static void setListener(@Nullable IRcvEventProcessor listener) {
        RcvEventDispatcher.listener = listener;
    }

    /**
     * This static public method will call from JNI to process the Receive-Event
     * by the listener
     *
     * @param channel CAN Channel to dispatch the event to.
     */
    static public void dispatchRcvEvent(@Nonnull TPCANHandle channel) {
        final IRcvEventProcessor lstnr = listener;
        if (lstnr != null)
            lstnr.processRcvEvent(channel);
    }
}
