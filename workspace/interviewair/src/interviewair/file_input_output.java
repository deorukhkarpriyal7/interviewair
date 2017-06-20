package interviewair;

import java.io.*;

public class file_input_output {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader in = null;
		FileWriter out = null;
		
		try {
			in = new FileReader("E:\\input.txt");
			out = new FileWriter("E:\\output.txt");
			
			int c;
			while((c = in.read()) != -1)
			{
				System.out.println((char)c);
				out.write(c);
			}
		}
		finally{
			if(in != null){
				in.close();
			}
			if (out != null){
				out.close();
			}
		}

	}

}
