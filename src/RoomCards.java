/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jermukuokkanen
 */
/**
 *
 * @author jermukuokkanen
 */


/**
 * This enum class represents a Room card in Cluedo game. There are nine rooms, Kitchen,
 * Ball room, Conservatory, Billard Room, Library, Study, Hall, Lounge, and Dining
 * Room.<br>
 * <br>
 Note that this class is also used to symbolically represent the RoomCards position in game.
 * 
 * @author Hector
 *
 */
public enum RoomCards implements Card {

    Kitchen, BallRoom, Conservatory, BillardRoom, Library, Study, Hall, Lounge, DiningRoom;

   
    @Override
    public char toStringOnBoard() {
      char s = ' ';
      return s;
    }

    /**
     * Get the location whose ordinal is index.
     * 
     * @param index
     *            --- the index (ordinal)
     * @return --- the location at the given index (ordinal)
     */
    public static RoomCards get(int index) {
        switch (index) {
        case 0:
            return Kitchen;
        case 1:
            return BallRoom;
        case 2:
            return Conservatory;
        case 3:
            return BillardRoom;
        case 4:
            return Library;
        case 5:
            return Study;
        case 6:
            return Hall;
        case 7:
            return Lounge;
        case 8:
            return DiningRoom;
        default:
            return DiningRoom;
        }
    }
}


