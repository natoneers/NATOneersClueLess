/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jermukuokkanen
 */
public enum WeaponCards implements Card {

    Candlestick, Dagger, LeadPipe, Revolver, Rope, Spanner;

    @Override
    public char toStringOnBoard() {
      char s = ' ';
      return s;
    }
    /**
     * Get the weapon whose ordinal is index.
     * 
     * @param index
     *            --- the index (ordinal)
     * @return --- the weapon at the given index (ordinal)
     */
    public static WeaponCards get(int index) {
        switch (index) {
        case 0:
            return Candlestick;
        case 1:
            return Dagger;
        case 2:
            return LeadPipe;
        case 3:
            return Revolver;
        case 4:
            return Rope;
        case 5:
            return Spanner;
        default:
            return Spanner;
        }
    }

}

