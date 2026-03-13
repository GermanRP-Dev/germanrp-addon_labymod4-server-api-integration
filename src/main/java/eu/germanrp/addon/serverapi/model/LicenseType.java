package eu.germanrp.addon.serverapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LicenseType {

    DRIVING("Führerschein"),
    WEAPON("Waffenschein"),
    PILOT("Pilotenschein"),
    ID("Personalausweis"),
    HUNTER("Jagdschein"),
    HEALTH_INSURANCE("Krankenkassenkarte");

    private final String displayName;

}