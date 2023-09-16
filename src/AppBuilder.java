interface App{
    void run() throws Exception;
}
public class AppBuilder implements App{
    private ArgumentsManager argumentsManager;
    private Reader reader;
    AppBuilder(String [] args) throws Exception {
        argumentsManager = new ArgumentsManager(args);
        getReader();
    }
    private void getReader() throws Exception {
        reader = new Reader();
        if (argumentsManager.isInteractive()) {
            reader.setForeverServant(true);
        }
        if (argumentsManager.getHashSummer() != null){
            reader.setHashSummer(argumentsManager.getHashSummer());
        }
        if (argumentsManager.getFileName() != null){
            reader.setFileName(argumentsManager.getFileName());
            if (argumentsManager.getHashSummer() == null){
                throw new Exception("Cant hash without chosen alg");
            }
            reader.setFileName(argumentsManager.getFileName());

        }
    }
    public void run() throws Exception {
        reader.run();
    }
}
