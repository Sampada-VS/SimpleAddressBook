import java.util.*;
import java.io.*;

	class Person {
		String firstName;
		String lastName;
		String address;
		String city;
		String state;
		String zip;
		String phone;

		Person (String firstName, String lastName, String address, String city, String state, String zip, String phone) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.phone = phone;
		}
		String getFirstName() {
			return firstName;
		}
		String getLastName() {
			return lastName;
		}
		String getAddress() {
			return address;
		}
		String getCity() {
			return city;
		}
		String getState() {
			return state;
		}
		String getZip() {
			return zip;
		}
		String getPhone() {
			return phone;
		}
		public String toString() {
			return "\n"+getFirstName()+" "+getLastName()+"\n"+getAddress()+"\n"+getCity()+" "+getState()+"-"+getZip()+"\n"+getPhone()+"\n";
		}
	}
	class AddressBook {
		ArrayList personInfo;

		public AddressBook() {
			personInfo = new ArrayList();
			getFile();
		}

		public void getFile() {
			String tokens[] = null;
			String fname,lname,addr,city,state,zip,ph;

			try {
				FileReader filereader = new FileReader ("contacts.txt");
				BufferedReader bufferedreader = new BufferedReader(filereader);

				String line = bufferedreader.readLine();
				while (line != null) {
					tokens = line.split(",");
					fname = tokens[0];
					lname = tokens[1];
					addr = tokens[2];
					city = tokens[3];
					state = tokens[4];
					zip = tokens[5];
					ph = tokens[6];

					Person p= new Person(fname,lname,addr,city,state,zip,ph);
					personInfo.add(p);
					line=bufferedreader.readLine();
				}
				bufferedreader.close();
				filereader.close();
			}
			catch(IOException e) {
				System.out.println(e);
			}
		}

		public void setFile() {
			try {
				Person p;
				String line;

				FileWriter filewriter = new FileWriter("contacts.txt");
				PrintWriter printwriter = new PrintWriter(filewriter);

				for (int i=0; i<personInfo.size(); i++) {
					p = (Person)personInfo.get(i);
					line = p.getFirstName() +","+ p.getLastName() +","+ p.getAddress() +","+ p.getCity() +","+ p.getState() +","+ p.getZip() +","+ p.getPhone();
					printwriter.println(line);
				}
				printwriter.flush();
				printwriter.close();
				filewriter.close();
			}
			catch(IOException e) {
				System.out.println(e);
			}
		}

		public void addPerson() {
			Scanner sc=new Scanner(System.in);
			System.out.print("\nEnter first name : ");
			String name=sc.nextLine();
			System.out.print("Enter last name : ");
			String surname=sc.nextLine();
			System.out.print("Enter address : ");
			String addr=sc.nextLine();
			System.out.print("Enter city : ");
			String cityName=sc.nextLine();
			System.out.print("Enter state : ");
			String stateName=sc.nextLine();
			System.out.print("Enter zip code : ");
			String zipCode=sc.nextLine();
			System.out.print("Enter mobile number : ");
			String phoneNo=sc.nextLine();
			System.out.println();

			Person p = new Person(name,surname,addr,cityName,stateName,zipCode,phoneNo);

			personInfo.add(p);
			System.out.println("Person added :\n"+p);
		}
		public void updatePerson (String n, String n1) {
			for (int i=0; i<personInfo.size(); i++) {
				Person p = (Person)personInfo.get(i);
				if ( n.equalsIgnoreCase(p.getFirstName()) && n1.equalsIgnoreCase(p.getLastName()) )
					personInfo.remove(i);
			}
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter address : ");
			String addr=sc.nextLine();
			System.out.print("Enter city : ");
			String cityName=sc.nextLine();
			System.out.print("Enter state : ");
			String stateName=sc.nextLine();
			System.out.print("Enter zip code : ");
			String zipCode=sc.nextLine();
			System.out.print("Enter mobile number : ");
			String phoneNo=sc.nextLine();
			System.out.println();

			Person p=new Person(n,n1,addr,cityName,stateName,zipCode,phoneNo);
			personInfo.add(p);
			System.out.println("Person updated :\n"+p);
		}
		public void sortName() {
			Collections.sort(personInfo, new FirstNameSorter());
			System.out.println(personInfo);
		}
		public void sortZip() {
			Collections.sort(personInfo, new ZipSorter());
			System.out.println(personInfo);
		}
		public void removePerson (String n, String n1) {
			for (int i=0; i<personInfo.size(); i++) {
				Person p = (Person)personInfo.get(i);
				if ( n.equalsIgnoreCase(p.getFirstName()) && n1.equalsIgnoreCase(p.getLastName()) )
					personInfo.remove(i);
			}
			System.out.println(personInfo);
		}
		public void printAll() {
			System.out.println("\nAll contacts in mailing label format :");
			for (int i=0; i<personInfo.size(); i++) {
				Person p = (Person)personInfo.get(i);
				System.out.println(p.getFirstName()+" "+p.getLastName());
				System.out.println(p.getAddress());
				System.out.println(p.getCity()+" "+p.getState()+"-"+p.getZip());
				System.out.println(p.getPhone()+"\n");
			}
		}
	}
	class FirstNameSorter implements Comparator<Person> {
		public int compare(Person p1, Person p2) {
			return p1.getFirstName().compareTo(p2.getFirstName());
		}
	}
	class ZipSorter implements Comparator<Person> {
		public int compare(Person p1, Person p2) {
			return p1.getZip().compareTo(p2.getZip());
		}
	}
public class SimpleAddressBook {

	public static void main (String[] args) {
		AddressBook addressbook = new AddressBook();
		String str,str1;
		int choice;
		Scanner sc=new Scanner(System.in);

		do {
			System.out.print(" 1.Add a person \n 2.Update person info \n 3.Delete a person \n 4.Sort by name \n 5.Sort by zip \n 6.Print all contacts \n 7.Exit \n Enter your choice : ");
			choice=sc.nextInt();
			switch (choice) {
				case 1:	addressbook.addPerson();
					break;
				case 2:	System.out.println("Enter first and last name to update info : ");
					str=sc.next();
					str1=sc.next();
					addressbook.updatePerson(str,str1);
					break;
				case 3:	System.out.print("Enter first and last name to delete a person :");
					str=sc.next();
					str1=sc.next();
					addressbook.removePerson(str,str1);
					System.out.println("Person named "+str+" "+str1+" deleted from address book.");
					break;
				case 4:	System.out.println("Address book is sorted by name.");
					addressbook.sortName();
					break;
				case 5:	System.out.println("Address book is sorted by zip code.");
					addressbook.sortZip();
					break;
				case 6:	addressbook.printAll();
					break;
				case 7:	System.out.println("You exited the program.");
					addressbook.setFile();
					System.exit(0);
					break;
				default:	System.out.println("Wrong choice.");
			}
		}while (choice != 7);
	}
}

