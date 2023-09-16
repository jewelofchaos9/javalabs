import org.apache.commons.cli.*;
public class ArgumentsManager {
    public HashSummer getHashSummer() {
        return hashSummer;
    }
    public boolean isInteractive() {
        return interactive;
    }
    public String getFileName() {
        return fileName;
    }
    private HashSummer hashSummer;
    private boolean interactive;
    private String fileName;

    ArgumentsManager(String [] args) throws Exception {
        parseArguments(args);
    }
    private static Options cliArgumentOptions() {
        Options options = new Options();

        OptionGroup hashGroup = new OptionGroup();
        Option md5 = new Option("M", "md5", false, "md5 hashsum");
        Option sha256= new Option("S", "sha256", false, "sha256 hashsum");
        Option interactive = new Option("i", "interactive", false, "interactive mode");
        hashGroup.addOption(md5);
        hashGroup.addOption(sha256);
        hashGroup.addOption(interactive);
        options.addOptionGroup(hashGroup);

        OptionGroup typeGroup = new OptionGroup();
        Option filePath = new Option("f", "file", true, "file path");
        typeGroup.addOption(interactive);
        typeGroup.addOption(filePath);
        typeGroup.setRequired(true);
        options.addOptionGroup(typeGroup);

        return options;
    }
    private void parseArguments(String[] args) throws Exception {
        Options options = cliArgumentOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e){
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("hashsums", options);
            System.exit(1);
        }

        if (cmd.hasOption("md5")) {
            this.hashSummer = HashSummerFactory.getInstance("md5");
        }
        if (cmd.hasOption("sha256")) {
            this.hashSummer = HashSummerFactory.getInstance("sha256");
        }
        if (cmd.hasOption("interactive")){
            this.interactive = true;
        }
        if (cmd.hasOption("file")){
            this.fileName = cmd.getOptionValue("file");
        }
    }
    public static void main(String [] args) throws Exception {
        System.out.println("zdarova");
        Options options = cliArgumentOptions();
        HashSummer hashSummer = HashSummerFactory.getInstance("md5");
        System.out.println(hashSummer.hexDigest("hui".getBytes()));
    }
}