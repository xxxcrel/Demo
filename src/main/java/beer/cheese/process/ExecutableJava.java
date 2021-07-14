package beer.cheese.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutableJava {
    public static void main(String[] args) throws IOException {
        System.out.println("args: " + args[args.length - 1]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("stdin: " + reader.readLine());
    }
}
