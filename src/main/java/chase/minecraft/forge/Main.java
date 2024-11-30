package chase.minecraft.forge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        new File("forge-universal.txt").delete();
        log("Starting the universal logger");
        if (args.length < 1)
        {
            log("Insufficient arguments provided. Expected <userArgs> <osArgs> <minecraft-args>");
            System.out.println("Usage: java -jar <jarfile> <userArgs> <osArgs> <minecraft-args>");
            System.exit(1);
        }

        String userArgs = new File(args[0]).getAbsolutePath().replaceAll("\\\\", "/");
        String osArgs = new File(args[1]).getAbsolutePath().replaceAll("\\\\", "/");

        String[] minecraftArgs = new String[args.length - 2];
        System.arraycopy(args, 1, minecraftArgs, 0, minecraftArgs.length);
        String currentJavaRuntime = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

        if (!new File(currentJavaRuntime).exists())
        {
            log("Java runtime not found. Exiting.");
            System.exit(1);
        }

        if (!new File(userArgs).exists())
        {
            logf("UserArgs file not found in %s. Exiting.", userArgs);
            System.exit(1);
        }

        if (!new File(osArgs).exists())
        {
            logf("OsArgs file not found in %s. Exiting.", osArgs);
            System.exit(1);
        }

        logf("Starting new Java process. Runtime: %s, UserArgs: %s, OsArgs: %s%n", currentJavaRuntime, userArgs, osArgs);
        ProcessBuilder pb = new ProcessBuilder(currentJavaRuntime, "@%s".formatted(userArgs), "@%s".formatted(osArgs));
        pb.command().addAll(java.util.Arrays.asList(minecraftArgs));

        // Redirect the process's output and error streams
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("forge-universal.txt")));
        pb.redirectError(ProcessBuilder.Redirect.appendTo(new File("forge-universal.txt")));

        Process p = pb.start();
        try
        {
            p.waitFor();
            log("Process ended successfully.");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            log("Process interrupted.");
        }
    }

    static void log(String message)
    {
        try (PrintWriter log = new PrintWriter(new FileWriter("forge-universal.txt", true)))
        {
            log.println(message);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static void logf(String format, Object... args)
    {
        try (PrintWriter log = new PrintWriter(new FileWriter("forge-universal.txt", true)))
        {
            log.printf(format, args);
            log("\n");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}