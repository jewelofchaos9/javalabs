import java.io.*;

public class Cli {
    public void setHashSummer(HashSummer hashSummer) {
        this.hashSummer = hashSummer;
        this.hashNeedToBeChosen = false;
    }
    private boolean hashNeedToBeChosen;
    Cli(){}
    HashSummer hashSummer;
    public void hashFile(String filename, HashSummer hashSummer) throws IOException {
        File file = new File(filename);
        if(file.exists() && file.isFile()){
            InputStream inputStream = new FileInputStream(file);
            byte []data = inputStream.readAllBytes();
            System.out.println(hashSummer.hexDigest(data));
        }
        else{
            System.out.println("File not found :(");
        }
    }
    private void serveForever() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (hashNeedToBeChosen) {
                System.out.println("USAGE: [md5/sha256] FILEPATH");
                String[] params = reader.readLine().strip().split(" ");
                if (params.length != 2) throw new Exception("User idiot");
                hashSummer = HashSummerFactory.getInstance(params[0]);
                hashFile(params[1], hashSummer);
            } else {
                System.out.println("USAGE: FILEPATH");
                String fileName = reader.readLine().strip();
                hashFile(fileName, hashSummer);
            }
        }
    }
    public void run() throws Exception {
        if(hashSummer == null) {
            hashNeedToBeChosen = true;
        }
        serveForever();
    }
}
