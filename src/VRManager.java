package src;

import java.util.ArrayList;
import java.util.List;

public class VRManager {
    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Video> getVideos() {
        return videos;
    }

    private List<Customer> customers = new ArrayList<Customer>() ;

    private List<Video> videos = new ArrayList<Video>() ;
    public VRManager(List<Customer> customers, List<Video> videos) {
        this.customers = customers;
        this.videos = videos;
    }

    public void doClearRentals(Customer foundCustomer) {
        System.out.println("Name: " + foundCustomer.getName() +
                "\tRentals: " + foundCustomer.getRentals().size()) ;
        for ( Rental rental: foundCustomer.getRentals() ) {
            System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
            System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
        }

        List<Rental> rentals = new ArrayList<Rental>() ;
        foundCustomer.setRentals(rentals);
    }

    public void doReturnVideo(Customer foundCustomer, String videoTitle) {
        List<Rental> customerRentals = foundCustomer.getRentals() ;
        for ( Rental rental: customerRentals ) {
            if ( rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented() ) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break ;
            }
        }
    }

    public void doRentVideo(Customer foundCustomer, String videoTitle) {
        Video foundVideo = null ;
        for ( Video video: videos ) {
            if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
                foundVideo = video ;
                break ;
            }
        }

        if ( foundVideo == null ) return;

        Rental rental = new Rental(foundVideo) ;
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals() ;
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);
    }

    public Customer findCustomer(String customerName) {
        Customer foundCustomer = null ;
        for ( Customer customer: customers ) {
            if ( customer.getName().equals(customerName)) {
                foundCustomer = customer ;
                break ;
            }
        }
        return foundCustomer;
    }

}
