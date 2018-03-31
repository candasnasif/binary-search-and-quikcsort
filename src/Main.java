import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		String data;
		String command;
		if (args[0].contains("csv")) {
			data = args[0];
			command = args[1];
		} else {
			data = args[1];
			command = args[0];
		}

		ArrayList<People> records = new ArrayList<People>();
		Register registerOp = new Register();
		registerOp.RegisterOp(records, data);/* register all data */
		command commandOperation = new command();
		commandOperation.commandOperation(records, command);/* send datas for execute command operation */
	}

}
