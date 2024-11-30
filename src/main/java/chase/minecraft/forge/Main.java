package chase.minecraft.forge;

import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        if (args.length < 1)
        {
            System.out.println("Usage: java -jar <jarfile> <userArgs> <osArgs> <minecraft-args>");
            System.exit(1);
        }

        String userArgs = args[0];
        String osArgs = args[1];

        String[] minecraftArgs = new String[args.length - 2];
        System.arraycopy(args, 1, minecraftArgs, 0, minecraftArgs.length);
        String currentJavaRuntime = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";


        // start new java process with the universalForgePath as the jar file and osArgsContents as the java arguments and the minecraftArgs as the arguments to the forge jar
        ProcessBuilder pb = new ProcessBuilder(currentJavaRuntime, "@%s".formatted(userArgs), "@%s".formatted(osArgs));
        pb.command().addAll(java.util.Arrays.asList(minecraftArgs));
        pb.inheritIO();
        Process p = pb.start();
        try
        {
            p.waitFor();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}