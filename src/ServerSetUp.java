/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose | Templates
 * and open the template in the editor.
 */
//package com.natoneers.client;

/**
 *
 * @author jermukuokkanen
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jermukuokkanen
 */

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JFrame;

/*
 * The server that can be run both as a console application or a GUI
 */
public class ServerSetUp {
    // a unique ID for each connection

    private static int uniqueId;
    // an ArrayList to keep the list of the Client
    private ArrayList<ClientThread> PlayerList;
    // if I am in a GUI
    private GUIServer sg;
    // to display time
    private SimpleDateFormat sdf;
    // the port number to listen for connection
    private int port;
    // the boolean that will be turned of to stop the server
    private boolean keepGoing;
    
    //Hardcode number of players for now
    private  final int constNumPlayers = 3;

   //Keep track of the number of players
    private int numPlayers;
    /*
	 *  server constructor that receive the port to listen to for connection as parameter
	 *  in console
     */
    public ServerSetUp(int port) {
        this(port, null);
    }

    public ServerSetUp(int port, GUIServer sg) {
        // GUI or not
        this.sg = sg;
        // the port
        this.port = port;
        // to display hh:mm:ss
        sdf = new SimpleDateFormat("HH:mm:ss");
        // ArrayList for the Client list
        PlayerList = new ArrayList<ClientThread>();
    }

    public void start() {
        keepGoing = true;
        /* create socket server and wait for connection requests */
        try {
            // the socket used by the server
            ServerSocket serverSocket = new ServerSocket(port);

            // infinite loop to wait for connections
            while (keepGoing) {
                // format message saying we are waiting
                display("Server waiting for Clients on port " + port + ".");

                Socket socket = serverSocket.accept();  	// accept connection
                // if I was asked to stop
                if (!keepGoing) {
                    break;
                }
                ClientThread t = new ClientThread(socket);  // make a thread of it
                PlayerList.add(t);			   // save it in the ArrayList
                t.start();
            }
            // I was asked to stop
            try {
                serverSocket.close();
                for (int i = 0; i < PlayerList.size(); ++i) {
                    ClientThread tc = PlayerList.get(i);
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException ioE) {
                        // not much I can do
                    }
                }
            } catch (Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        } // something went bad // something went bad
        catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }

    /*
     * For the GUI to stop the server
     */
    protected void stop() {
        keepGoing = false;
        // connect to myself as Client to exit statement 
        // Socket socket = serverSocket.accept();
        try {
            new Socket("localhost", port);
        } catch (Exception e) {
            // nothing I can really do
        }
    }

    /*
	 * Display an event (not a message) to the console or the GUI
     */
    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        if (sg == null) {
            System.out.println(time);
        } else {
            sg.appendEvent(time + "\n");
        }
    }

    /*
	 *  to broadcast a message to all Clients
     */
    protected synchronized void broadcast(String message) {
        // add HH:mm:ss and \n to the message
        String time = sdf.format(new Date());
        String messageLf = time + " " + message + "\n";
        // display message on console or GUI
        if (sg == null) {
            System.out.print(messageLf);
        } else {
            sg.appendRoom(messageLf);     // append in the room window
        }
        // we loop in reverse order in case we would have to remove a Client
        // because it has disconnected
        for (int i = PlayerList.size(); --i >= 0;) {
            ClientThread ct = PlayerList.get(i);
            // try to write to the Client if it fails remove it from the list
            if (!ct.writeMsg(messageLf)) {
                PlayerList.remove(i);
                display("Disconnected Client " + ct.ipAddress + " removed from list.");
            }
        }
    }
    
     protected synchronized void startGame(){
         
         new GameThread(numPlayers,this);
         
     }

    // for a client who logoff using the LOGOUT message
    synchronized void remove(int id) {
        // scan the array list until we found the Id
        for (int i = 0; i < PlayerList.size(); ++i) {
            ClientThread ct = PlayerList.get(i);
            // found it
            if (ct.id == id) {
                PlayerList.remove(i);
                return;
            }
        }
    }

    /*
	 *  To run as a console application just open a console window and: 
	 * > java ServerSetUp
	 * > java ServerSetUp portNumber
     */
    public static void main(String[] args) {
        // start server on port 59001 unless a PortNumber is specified 
        int portNumber = 59001;
        switch (args.length) {
            case 1:
                try {
                    portNumber = Integer.parseInt(args[0]);
                } catch (Exception e) {
                    System.out.println("Invalid port number.");
                    System.out.println("Usage is: > java Server [portNumber]");
                    return;
                }
            case 0:
                break;
            default:
                System.out.println("Usage is: > java Server [portNumber]");
                return;

        }
        // create a server object and start it
        ServerSetUp server = new ServerSetUp(portNumber);
        server.start();
    }

    /**
     * One instance of this thread will run for each client
     */
    class ClientThread extends Thread {
        // the socket where to listen/talk

        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        // my unique id (easier for deconnection)
        int id;
        // the ipAddress of the Client
        String ipAddress;
        // PlayerName
        String playerName;
        // Player number
        int playerNumber;
        //Game message types
        GameMessage gameMessage;
        // Time connected
        String date;

        // Constructor
        ClientThread(Socket socket) {
            // a unique id
            id = ++uniqueId;
            this.socket = socket;
            /* Creating both Data Stream */
            System.out.println("Thread trying to create Object Input/Output Streams");
            try {
                // create output first
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                // read the ipAddress
                ipAddress = (String) sInput.readObject();
                display(ipAddress + " just connected.");
                //Show player who just joined all other Players who have joined
               
                writeMsg("Current Players: \n");
                for (int i = 0; i < PlayerList.size(); ++i) {
                    ClientThread ct = PlayerList.get(i);
                    if (!ct.playerName.isEmpty())
                        writeMsg((i + 1) + ") " + ct.playerName + " since " + ct.date);
                }
            } catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            } // Catch ClassNotFoundException
            catch (ClassNotFoundException e) {
            }
            date = new Date().toString() + "\n";
        }

        // what will run forever
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while (keepGoing) {
                // read a String (which is an object)
                try {
                    gameMessage = (GameMessage) sInput.readObject();
                } catch (IOException e) {
                    display(ipAddress + " Exception reading Streams: " + e);
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }
                // the messaage part of the GameMessage
                String message = gameMessage.getMessage();

                // Switch on the type of message receive
                switch (gameMessage.getType()) {

                    case GameMessage.MESSAGE:
                        //broadcast(ipAddress + ": " + message);
                        break;
                    case GameMessage.LOGOUT:
                        display(ipAddress + " disconnected with a LOGOUT message.");
                        keepGoing = false;
                        break;
                    case GameMessage.WHOISIN:
                        writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                        // scan PlayerList the users connected
                        for (int i = 0; i < PlayerList.size(); ++i) {
                            ClientThread ct = PlayerList.get(i);
                            writeMsg((i + 1) + ") " + ct.ipAddress + " since " + ct.date);
                        }
                        break;
                    case GameMessage.JOIN:
                        // playerName
                        int i = message.indexOf(' ');
                        playerName = message.substring(0, i);
                        broadcast(ipAddress + ": " + message);
                        numPlayers++;
                        //broadcast(message);
                        broadcast("Number of Players Joined: " + numPlayers );
                        int numPlayersNeeded = constNumPlayers-numPlayers;
                        if(numPlayers < 3)
                            broadcast("Number of Players Needed to Start Game: " + numPlayersNeeded );
                        else{
                            broadcast("Game Starting");
                            startGame();}
                        break;
                        
                }
            }
            // remove myself from the arrayList containing the list of the
            // connected Clients
            remove(id);
            close();
        }

        // try to close everything
        private void close() {
            // try to close the connection
            try {
                if (sOutput != null) {
                    sOutput.close();
                }
            } catch (Exception e) {
            }
            try {
                if (sInput != null) {
                    sInput.close();
                }
            } catch (Exception e) {
            };
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
            }
        }

        /*
		 * Write a String to the Client output stream
         */
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if (!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            } // if an error occurs, do not abort just inform the user
            catch (IOException e) {
                display("Error sending message to " + ipAddress);
                display(e.toString());
            }
            return true;
        }
    }
}
