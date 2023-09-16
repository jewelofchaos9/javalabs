public class Main {
    public static void main(String[] argv) {
        try {
            App app = new AppBuilder(argv);
            app.run();
        } catch (Exception e) {
            System.out.println("Program exitted with exception:");
            System.out.println(new RuntimeException(e));
        }
    }
}
