package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VRUI {
	private static Scanner scanner = new Scanner(System.in) ;

	private static VRManager manager = new VRManager(new ArrayList<Customer>(), new ArrayList<Video>());;

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				case 3: ui.registerCustomer(); break ;
				case 4: ui.registerVideo(); break ;
				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break;
				case 8: ui.clearRentals() ; break ;
				case -1: ui.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void clearRentals() {
		String customerName = enterCustomerName();

		Customer foundCustomer = manager.findCustomer(customerName);
		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			manager.doClearRentals(foundCustomer);
		}
	}

	public void returnVideo() {
		String customerName = enterCustomerName();

		Customer foundCustomer = manager.findCustomer(customerName);
		if (foundCustomer == null) return;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		manager.doReturnVideo(foundCustomer, videoTitle);
	}

	private String enterCustomerName() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		return customerName;
	}


	private void init() {
		List<Customer> customers = new ArrayList<Customer>() ;

		List<Video> videos = new ArrayList<Video>() ;

		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;

		Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
		Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
		videos.add(v1) ;
		videos.add(v2) ;

		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;

		james.addRental(r1) ;
		james.addRental(r2) ;

		v1.setRented(true);
		v2.setRented(true);

		manager = new VRManager(customers, videos);
	}

	public void listVideos() {
		System.out.println("List of videos");

		for ( Video video: manager.getVideos() ) {
			System.out.println("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");
		for ( Customer customer: manager.getCustomers() ) {
			System.out.println("Name: " + customer.getName() +
					"\tRentals: " + customer.getRentals().size()) ;
			for ( Rental rental: customer.getRentals() ) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}
		}
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		String customerName = enterCustomerName();

		Customer foundCustomer = manager.findCustomer(customerName);

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			String result = foundCustomer.getReport() ;
			System.out.println(result);
		}
	}

	public void rentVideo() {
		String customerName = enterCustomerName();

		Customer foundCustomer = manager.findCustomer(customerName);
		if (foundCustomer == null) return;

		System.out.println("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		manager.doRentVideo(foundCustomer, videoTitle);
	}

	private void registerVideo() {
		System.out.println("Enter video title to register: ") ;
		String title = scanner.next() ;

		System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
		int videoType = scanner.nextInt();

		System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
		int priceCode = scanner.nextInt();

		manager.doRegisterVideo(title, videoType, priceCode);
	}

	private void registerCustomer() {
		String name = enterCustomerName();
		manager.doRegisterCustomer(name);
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;

	}
}
