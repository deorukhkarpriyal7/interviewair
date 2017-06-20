package interviewair;

import java.io.*;

public class file_input_output_with_BufferedReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		FileReader in = new FileReader("E:\\input.txt");
		BufferedReader bufferedReader = new BufferedReader(in);
		FileWriter out = new FileWriter("E:\\output.txt",true);
		BufferedWriter bufferedWriter = new BufferedWriter(out);
		
		String c;
		while((c = bufferedReader.readLine()) != null){
			System.out.println(c);
			//out.write(c);
			bufferedWriter.write(c);
			}
		in.close();
		bufferedWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
