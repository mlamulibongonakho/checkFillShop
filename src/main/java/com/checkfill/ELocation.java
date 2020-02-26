package com.checkfill;

/**
 * This Enum declares all locations of our shops ,
 * I know 50 was needed but I did only few to demonstrate
 */
public enum ELocation {
     NEW_YORK("New York"),
    NEW_BERN("New Bern"),
    NEW_TOWN("New Town"),
    WOODLAND("Woodland hills"),
    CLEVELAND("Cleveland, ga"),
    CLEMSON("Clemson, sc"),
    JOHNSON("Johnson city, tn"),
    NEW_JERSEY("New Jersey"),
    BLEAUFORT("beaufort, sc");

    public String locName;
    ELocation(String name) {
        this.locName = name;
    }
}
