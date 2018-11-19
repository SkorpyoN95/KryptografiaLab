package pl.edu.agh;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    public static void main(String[] args) {
	try{
	    
	    byte[] input = Files.readAllBytes(new File(args[1]).toPath());
	    CipherMachine machine = new CipherMachine();

	    if (args[0].contains("enc")) {
			FileOutputStream os = new FileOutputStream(args[2]);
			System.out.println("Encrypting file!");

			long timeStart = System.nanoTime();
			byte[] encrypted = machine.encrypt(input);
			long timeStop = System.nanoTime();

			os.write(encrypted);
			System.out.println("Encrypted in " + (timeStop - timeStart) + "[ns]");

	    } else if(args[0].contains("dec")) {
			System.out.println("Decrypting file!");

            long timeStart = System.nanoTime();
            byte[] decrypted = machine.decrypt(input);
            long timeStop = System.nanoTime();

            System.out.println("Decrypted in " + (timeStop - timeStart) + "[ns]");

			System.out.println(new String(decrypted, UTF_8));

	    } else System.out.println("Undefined command!");

	}
	catch(Exception e){
	    e.printStackTrace();
	}
    }
}
