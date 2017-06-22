import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("input"));
		Scanner user = new Scanner(new File("user"));
		Scanner scan = new Scanner(System.in);
		//Using array as not allowed to use ArrayList
		//Optionally can use ArrayList to scale up and resize when needed
		Product[] product = new Product[100];
		Product[] searchedFor = new Product[100];
		User[] users = new User[100];
		int totalUsers = 0;
		int totalSearchedFor = 0;
		int totalProducts = 0;
		Product choose = new Product();
		while(input.hasNext())
		{
			String[] info = input.nextLine().split(",");
			product[totalProducts] = new Product();
			product[totalProducts].setName(info[0]);
			product[totalProducts].setPrice(Integer.parseInt(info[1]));
			product[totalProducts].setCategory(info[2]);
			product[totalProducts++].setPopularity(Integer.parseInt(info[3]));
		}
		while(user.hasNext())
		{
			String[] info = user.nextLine().split(",");
			users[totalUsers] = new User();
			users[totalUsers].setName(info[0]);
			users[totalUsers].setPassword(Integer.parseInt(info[1]));
			users[totalUsers].setBudget(Integer.parseInt(info[2]));
			users[totalUsers++].setAddress(info[3]);
		}
		//Search method cannot be in Product class as it considers a range of products not one
		System.out.println("Enter a product to search(For eg: TeeShirt)");
		String category = scan.nextLine();
		for(int i=0;i<totalProducts;i++)
		{
			if(product[i].getCategory().equals(category))
			{
				searchedFor[totalSearchedFor++] = product[i];
				System.out.println("Name: "+product[i].getName()+", Price: "+product[i].getPrice()+", Popularity: "+product[i].getPopularity());
			}
		}
		if(totalSearchedFor==0)
		{
			System.out.println("Product not available");
		}
		//Sorting searched items by bubble sort in descending order
		//Optionally can use quick sort to scale up
		if(totalSearchedFor!=0)
		{
		System.out.println("Sort from highest to lowest by writing either price or popularity");
		String choice = scan.nextLine();
		if(choice.equals("price"))
		{
			for(int i=0;i<totalSearchedFor-1;i++)
			{
				for(int j=0;j<totalSearchedFor-i-1;j++)
				{
					if(searchedFor[j].getPrice()<searchedFor[j+1].getPrice())
					{
						Product temp = new Product();
						temp = searchedFor[j];
						searchedFor[j] = searchedFor[j+1];
						searchedFor[j+1] = temp;
					}
				}
			}
			for(int i=0;i<totalSearchedFor;i++)
			{
				System.out.println("Name: "+searchedFor[i].getName()+", Price: "+searchedFor[i].getPrice()+", Popularity: "+searchedFor[i].getPopularity());
			}
		}
		else if(choice.equals("popularity"))
		{
			for(int i=0;i<totalSearchedFor-1;i++)
			{
				for(int j=0;j<totalSearchedFor-i-1;j++)
				{
					if(searchedFor[j].getPopularity()<searchedFor[j+1].getPopularity())
					{
						Product temp = new Product();
						temp = searchedFor[j];
						searchedFor[j] = searchedFor[j+1];
						searchedFor[j+1] = temp;
					}
				}
			}
			for(int i=0;i<totalSearchedFor;i++)
			{
				System.out.println("Name: "+searchedFor[i].getName()+", Price: "+searchedFor[i].getPrice()+", Popularity: "+searchedFor[i].getPopularity());
			}
		}
		else
			System.out.println("Command not clear");
		//Assuming User buys only one product
		System.out.println("For choosing a product write name");
		String chosen = scan.nextLine();
		for(int i=0;i<totalSearchedFor;i++)
		{
			if(searchedFor[i].getName().equals(chosen))
			{
				choose = searchedFor[i];
				//if we search for a product its popularity increases
				searchedFor[i].setPopularity(searchedFor[i].getPopularity()+1);
			}
		}
		//Assuming Shipping is equivalent to buying and password is a number 
		System.out.println("Give your id and password to buy(For eg: Tom 12345)");
		String[] info = scan.nextLine().split(" ");
		String name = info[0];
		int password = Integer.parseInt(info[1]);
		for(int i=0;i<totalUsers;i++)
		{
			if(users[i].getName().equals(name) && users[i].getPassword()==password)
			{
				System.out.println("Login successful");
				if(choose.getPrice()<=users[i].getBudget())
				{
					System.out.println("Shipped");
				}
				else
				{
					System.out.println("Out of Budget");
				}
			}
		}
		//Removing the product from list
		//Optionally can convert to ArrayList and use remove method
		boolean[] remove = new boolean[totalProducts];
		int remaining = 0;
		for(int i=0;i<totalProducts;i++)
		{
			if(product[i]==choose)
			{
				remove[i] = true;
			}
			else
			{
				remove[i] = false;
				remaining++;
			}
		}
		Product[] remainingProducts = new Product[remaining];
		int index = 0;
		for(int i=0;i<remaining;i++)
		{
			if(!remove[i])
			{
				remainingProducts[index++] = product[i];
			}
		}
		//Verifying remaining products
		/*for(int i=0;i<index;i++)
		{
			System.out.println(remainingProducts[i].getName()+remainingProducts[i].getPopularity());
		}*/
		

	}

}
}
