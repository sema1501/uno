public class Moderator {

    private static final int MAX_PLAYERS = 4;
    private Player player1;
    private Deck deck;
    public CircularDoublyLinkedList queue;
    private static int botCount = 1;
    private boolean playing = true;

    // Main method to start the game
    public static void main(String[] args) {
        Moderator moderator = new Moderator();
        moderator.InitializeGame();
        moderator.PlayGame();
    }

    // Constructor to initialize the moderator
    public Moderator() {
        this.queue = new CircularDoublyLinkedList();
    }

    // Method to create player and bots and initialize the game
    public void InitializeGame() {
        this.deck = new Deck();
        this.player1 = new Player(deck, this);
        queue.addNode(player1);
        ;
        player1.createUI();
        while (botCount < MAX_PLAYERS) {
            addBot();
        }
        for (int i = 0; i < 7; i++) {
            queue.forEach(player -> player.drawCard());
        }
        deck.updateTopCard(deck.drawCard());

    }

    // Method to start and play the game
    public void PlayGame() {
        System.out.println("Game started");
        while (playing) {
            queue.forEach(player -> {
                player.doMove();
                didPlayerWin(player);// NEEDS TO BE IMPLEMENTED
            });
        }
    }

    // Method to add a bot to the game
    public void addBot() {
        if (botCount < MAX_PLAYERS) {
            queue.addNode(new Bot(deck, this, "Bot" + botCount));
            botCount++;
        }
    }

    /*
     * Method to get the next player of the given player in the queue
     * 
     * @param p The player whose next player is to be found
     * 
     * @return The next player in the queue
     */
    public Player getNextPlayer(Player p) {
        return queue.get(p).next.data;
    }

    /*
     * Method to block the given player
     * 
     * @param p The player to block
     */
    public void blockPlayer(Player p) {
        p.blocked = true;
    }

    // NEEDS TO BE IMPLEMENTED*-*
    public void didPlayerWin(Player p) {
        /*
         * Checks if player has any cards left and sets playing to false if player has
         * no cards left
         */
    }
}
