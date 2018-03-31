import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class command {
	public void commandOperation(ArrayList<People> a,String command) throws IOException {
		long startTime=System.currentTimeMillis();
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		BufferedReader br = new BufferedReader(new FileReader(command));
		String line;
		while ((line = br.readLine()) != null) {
			String[] parts=line.split(" ");
			writer.println("CommandText: "+(char)34+line+(char)34);
			writer.println("Result:");
			ArrayList<People> search=new ArrayList<People>();
			search=a;
			for (int i = 3; i < parts.length; i=i+2) {
				search=searching(search, parts[i]);
			}
			if(search.size()>=1)writer.println();
			String[] coulmn=parts[1].split(",");
			int spaceCID=dataLengthCalculateString(search, "CID")+1;
			int spaceFirstName=dataLengthCalculateString(search, "FirstName")+1;/*calculate max words length for last seen datas*/
			int spaceLastName=dataLengthCalculateString(search, "LastName")+1;
			int spaceCity=dataLengthCalculateString(search, "City")+1;
			int spaceAddressLine1=dataLengthCalculateString(search, "AddressLine1")+1;
			int spaceSocialSecurityNumber=dataLengthCalculateString(search, "SocialSecurityNumber")+1;
			int length=0;
			for (int i = 0; i < coulmn.length; i++) {
				if(search.size()<1){
					writer.print("Empty Rowset");
					length=length+"Empty Rowset".length();
					break;
				}
				else if(coulmn[i].equals("FirstName")){
					writer.print(String.format("%"+(-1)*spaceFirstName+"s",coulmn[i]));
					length=length+String.format("%"+(-1)*spaceFirstName+"s",coulmn[i]).length();
				}
				else if(coulmn[i].equals("LastName")){												/*Print first line for all results*/
					writer.print(String.format("%"+(-1)*spaceLastName+"s",coulmn[i]));
					length=length+String.format("%"+(-1)*spaceLastName+"s",coulmn[i]).length();
				}
				else if(coulmn[i].equals("City")){
					writer.print(String.format("%"+(-1)*spaceCity+"s",coulmn[i]));
					length=length+String.format("%"+(-1)*spaceCity+"s",coulmn[i]).length();
				}
				else if(coulmn[i].equals("AddressLine1")){
					writer.print(String.format("%"+(-1)*spaceAddressLine1+"s",coulmn[i]));
					length=length+String.format("%"+(-1)*spaceAddressLine1+"s",coulmn[i]).length();
				}
				else if(coulmn[i].equals("CID")){
					writer.print(String.format("%"+(-1)*spaceCID+"s",coulmn[i]));
					length=length+String.format("%"+(-1)*spaceCID+"s",coulmn[i]).length();
				}
				else if(coulmn[i].equals("SocialSecurityNumber")){
					writer.print(String.format("%"+(-1)*spaceSocialSecurityNumber+"s",coulmn[i]));
					length=length+String.format("%"+(-1)*spaceSocialSecurityNumber+"s",coulmn[i]).length();
				}
				
			}
			writer.println();
			for (int i = 0; i < search.size(); i++) {
				for (int j = 0; j < coulmn.length; j++) {
					if(search.size()<1)break;
					else if(coulmn[j].equals("FirstName")) writer.print(String.format("%"+(-1)*spaceFirstName+"s", search.get(i).FirstName));	/*Print all results for each coulmn*/
					else if(coulmn[j].equals("LastName"))writer.print(String.format("%"+(-1)*spaceLastName+"s",search.get(i).LastName));
					else if(coulmn[j].equals("City")) writer.print(String.format("%"+(-1)*spaceCity+"s", search.get(i).City));
					else if(coulmn[j].equals("AddressLine1")) writer.print(String.format("%"+(-1)*spaceAddressLine1+"s",search.get(i).AddressLine1));
					else if(coulmn[j].equals("SocialSecurityNumber")) writer.print(String.format("%"+(-1)*spaceSocialSecurityNumber+"s",search.get(i).SocialSecurityNumber));
					else if(coulmn[j].equals("CID")) writer.print(String.format("%"+(-1)*spaceCID+"s", search.get(i).CID));
				}
				if(search.size()<1) break;
				writer.println();
			}
			long endTime=System.currentTimeMillis();
			long total=endTime-startTime;
			writer.println("--------------------");
			writer.println("Proccess time: "+total+" milliseconds");
			writer.println();
		}
		
		
		writer.close();
		
	}
	public ArrayList<People> searching(ArrayList<People> a,String x) throws IOException{
		String[] parts;
		ArrayList<People> result=new ArrayList<>();
		if(x.contains("<")){
			parts=x.split("<");/*if we have < for example SocialSecurityNumber<21324213 than split like that*/
			if(parts[0].equals("SocialSecurityNumber"))
			for (int i = 0; i < a.size(); i++) {
				
					if(parts[1].contains("=")){

						if((Integer.parseInt(a.get(i).SocialSecurityNumber))<=Integer.parseInt((parts[1].substring(1, parts[1].length())))){
							result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
						}
					}
					else{
						
						if((Integer.parseInt(a.get(i).SocialSecurityNumber))<Integer.parseInt((parts[1]))){
								result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
						}
						
					}
			}
			else if(parts[0].equals("CID")){
				for (int i = 0; i < a.size(); i++) {
					
					if(parts[1].contains("=")){
						if((Integer.parseInt(a.get(i).CID))<=Integer.parseInt((parts[1].substring(1, parts[1].length()-1)))){
							result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
						}
					
					}
					else{
							if((Integer.parseInt(a.get(i).CID))<Integer.parseInt((parts[1]))){
								result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
							}
						
					}
				
			}
			}
		
			
		}
		else if(x.contains(">")){
			parts=x.split(">");
			if(parts[0].equals("SocialSecurityNumber"))
				for (int i = 0; i < a.size(); i++) {
					
						if(parts[1].contains("=")){
							if((Integer.parseInt(a.get(i).SocialSecurityNumber))>=Integer.parseInt((parts[1].substring(1, parts[1].length())))){
								result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
							}
						
						}
						else{
							
								if((Integer.parseInt(a.get(i).SocialSecurityNumber))>Integer.parseInt((parts[1]))){
									result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
								}
							
						}
					
				}
				else if(parts[0].equals("CID")){
					for (int i = 0; i < a.size(); i++) {
						
						if(parts[1].contains("=")){
			
							if((Integer.parseInt(a.get(i).CID))>=Integer.parseInt((parts[1].substring(1, parts[1].length()-1)))){
								result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
							}
						}
						else{
							
								if((Integer.parseInt(a.get(i).CID))>Integer.parseInt((parts[1]))){
									result.add(new People(a.get(i).CID,a.get(i).FirstName,a.get(i).LastName,a.get(i).City,a.get(i).AddressLine1,a.get(i).SocialSecurityNumber));
								}
							
						}
					
				}
				}
		}
		else{
			Register registerOp=new Register();/*We use binarysearch for find the choosing char for example ~ba*/
			Sort sorting=new Sort();
			parts=x.split("~");
			String argument=parts[1].toUpperCase();/*I converted arguments to uppercase.If we have ~Ba my code take the words starting with ba characters*/ 
			if(parts[0].equals("FirstName")){
				ArrayList<People> records=new ArrayList<People>();
				records=a;
				ArrayList<People> FirstName=new ArrayList<People>();
				FirstName=sorting.QuickSortString(records, 0, records.size()-1, "FirstName");
				result=compare(argument, FirstName, "FirstName");
			}
			else if(parts[0].equals("LastName")){
				ArrayList<People> records1=new ArrayList<People>();
				records1=a;
				ArrayList<People> LastName=new ArrayList<People>();
				LastName=sorting.QuickSortString(records1, 0, records1.size()-1, "LastName");
				result=compare(argument, LastName, "LastName");
			}
			else if(parts[0].equals("City")){
				ArrayList<People> records2=new ArrayList<People>();
				records2=a;
				ArrayList<People> City=new ArrayList<People>();
				City=sorting.QuickSortString(records2, 0, records2.size()-1, "City");
				result=compare(argument, City, "City");
			}
			else if(parts[0].equals("AddressLine1")){
				ArrayList<People> records3=new ArrayList<People>();
				records3=a;
				ArrayList<People> AddressLine1=new ArrayList<People>();
				AddressLine1=sorting.QuickSortString(records3, 0, records3.size()-1, "AddressLine1");
				result=compare(argument, AddressLine1, "AddressLine1");
			}
		}
		return result;
	}
	public ArrayList<People> compare(String a,ArrayList<People> list,String type){/*this function compare the datas with binary and linear search and return true distance of array*/
		ArrayList<People> result=new ArrayList<People>();
		BinarySearch binary=new BinarySearch();
		int index=binary.binarySearch(list, a, type);
		int j=0;
		for (int i = index; i < list.size(); i++) {
			int x=0;
			for (j = 0; j < a.length(); j++) {

				if(a.charAt(j)==list.get(i).get(type).charAt(j)||a.charAt(j)==Character.toUpperCase(list.get(i).get(type).charAt(j))){
					x=x+1;
				}
			}
			if(x==(a.length())){
				result.add(new People(list.get(i).CID,list.get(i).FirstName,list.get(i).LastName,list.get(i).City,list.get(i).AddressLine1,list.get(i).SocialSecurityNumber));
			}
		}
		return result;
	}
	public int dataLengthCalculateString(ArrayList<People> a,String type){/*I used this function for calculate largest words size in the array*/
		int max=0;
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i).get(type).length()>max)max=a.get(i).get(type).length();
		}
		if(type.length()<max)
		return max;
		else return type.length();
	}
}
