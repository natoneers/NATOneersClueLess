
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jermukuokkanen
 */
public class GameThread {
    
     private int numPlayers;
     private ServerSetUp serverThread ;
         /**
     * the solution created at beginning
     */
    private Suggestion solution;
    /**
    
    /**
     * number of dices
     */
    private int numDices;
    /**
     * all six players (including dummy tokens) as a list
     */
    private List<PlayerCards> players;
    /**
     * after cards are evenly dealt, all remaining cards are in this list.
     */
    private List<Card> remainingCards;
    /**
     * a random number generator
     */
    private static final Random RAN = new Random();
     
      GameThread(int numPlayers, ServerSetUp serverThread) {
         this.numPlayers = numPlayers;
         this.serverThread = serverThread;
         
         //Send message to Clients from Game Thread
         serverThread.broadcast("GameThread Started");
         
     }
      
    public void SetWinningCards() {
        remainingCards = new ArrayList<>();

        // let's get all Character cards first
        List<SuspectCards> suspectCards = new ArrayList<>(
                Arrays.asList(SuspectCards.values()));
        // randomly choose one as the murderer
        SuspectCards solCharacter = suspectCards
                .remove(RAN.nextInt(suspectCards.size()));
        // then put the rest character cards in the card pile
        remainingCards.addAll(suspectCards);

        // then let's get all Location cards
        List<RoomCards> roomCards = new ArrayList<>(Arrays.asList(RoomCards.values()));
        // randomly choose one as the crime scene
        RoomCards solLocation = roomCards.remove(RAN.nextInt(roomCards.size()));
        // then put the rest location cards in the card pile
        remainingCards.addAll(roomCards);

        // then let's get all Weapon cards
        List<WeaponCards> weaponCards = new ArrayList<>(Arrays.asList(WeaponCards.values()));
        // randomly choose one as the murder weapon
        WeaponCards solWeapon = weaponCards.remove(RAN.nextInt(weaponCards.size()));
        // then put the rest location cards in the card pile
        remainingCards.addAll(weaponCards);

        // now we have a solution
        solution = new Suggestion(solCharacter, solWeapon, solLocation);
    }
     
    
}
