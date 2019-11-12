/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jermukuokkanen
 */
public enum SuspectCards implements Card {
    ColonelMustard, MissScarlet, MrsPeacock,  MrsWhite, ProfessorPlum, TheReverendGreen ;
    
  @Override
    public char toStringOnBoard() {
        char s = ' ';
        switch (this.ordinal()) {
        case 0:
            s = 'S';
            break;
        case 1:
            s = 'M';
            break;
        case 2:
            s = 'W';
            break;
        case 3:
            s = 'G';
            break;
        case 4:
            s = 'C';
            break;
        case 5:
            s = 'P';
            break;
        default:
        }
        return s;
    }

    /**
     * This method returns the next character in turn. When current character ends turn,
     * It's useful for the game to know who the next acting character is.
     * 
     * @return --- the next character in turn.
     */
    public SuspectCards nextCharacter() {
        switch (this) {
        case ColonelMustard:
            return MrsWhite;
        case MissScarlet:
            return ColonelMustard;
        case MrsPeacock:
            return ProfessorPlum;
        case MrsWhite:
            return TheReverendGreen;
        case ProfessorPlum:
            return MissScarlet;
        case TheReverendGreen:
            return MrsPeacock;
        default:
            return null; // dead code
        }
    }

   
    /**
     * Get the character whose ordinal is index.
     * 
     * @param index
     *            --- the index (ordinal)
     * @return --- the character at the given index (ordinal)
     */
    public static SuspectCards get(int index) {
        switch (index) {
        case 0:
            return MissScarlet;
        case 1:
            return ColonelMustard;
        case 2:
            return MrsWhite;
        case 3:
            return TheReverendGreen;
        case 4:
            return MrsPeacock;
        case 5:
            return ProfessorPlum;
        default:
            return ColonelMustard;
        }
    }
}

