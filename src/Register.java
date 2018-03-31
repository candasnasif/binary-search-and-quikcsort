import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Register {
	public void RegisterOp(ArrayList<People> a, String data) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(data));
		String line;
		while ((line = br.readLine()) != null) {/* This function read the datas and register an arraylist */
			if (!line.contains("FirstName")) {
				String[] parts = line.split("\\|");
				a.add(new People(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
			}
		}
	}
}
