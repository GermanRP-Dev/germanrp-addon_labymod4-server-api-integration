package eu.germanrp.addon.serverapi.packet.atm;

import eu.germanrp.addon.serverapi.model.ATM;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateATMPacket extends ATMPacket {

    @Getter
    @Accessors(fluent = true)
    private String id;

    public UpdateATMPacket(final String id, final ATM atm) {
        super(atm);
        this.id = id;
    }

    @Override
    public void read(@NotNull PayloadReader reader) {
        this.id = reader.readString();
        super.read(reader);
    }

    @Override
    public void write(@NotNull PayloadWriter writer) {
        writer.writeString(this.id);
        super.write(writer);
    }

}
