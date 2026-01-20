package eu.germanrp.addon.serverapi.packet;

import eu.germanrp.addon.serverapi.model.ATM;

import java.util.Set;

/**
 * This packet is sent to the player exactly once when they join the server
 * to provide the initial list of all registered ATMs.
 */
public class RegisteredATMsPacket extends ATMListPacket {

    public RegisteredATMsPacket(Set<ATM> atms) {
        super(atms);
    }

}
