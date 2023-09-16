public class Reader {
    private HashSummer hashSummer;
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    private String fileName;
    public void setHashSummer(HashSummer hashSummer) {
        this.hashSummer = hashSummer;
    }

    public void setForeverServant(boolean foreverServant) {
        this.foreverServant = foreverServant;
    }

    private boolean foreverServant;
    Reader(){}
    public void run() throws Exception {
        // TODO add cli readers for hash+filename, filename if hash set
        // cli = ...
        Cli cli = new Cli();
        if (foreverServant) {
            cli.setHashSummer(hashSummer);
            cli.run();
        }
        else {
            cli.hashFile(fileName, hashSummer);
        }
    }
}
