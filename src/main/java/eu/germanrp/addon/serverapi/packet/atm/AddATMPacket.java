package eu.germanrp.addon.serverapi.packet.atm;

import eu.germanrp.addon.serverapi.model.ATM;
import lombok.NoArgsConstructor;

/**
 * This packet is sent to the player when a single ATM is added.
 */
@NoArgsConstructor
public class AddATMPacket extends ATMPacket {

    public AddATMPacket(ATM atm) {
        super(atm);
    }

}
