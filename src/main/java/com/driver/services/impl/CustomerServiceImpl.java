package com.driver.services.impl;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.model.TripBooking;
import com.driver.model.TripStatus;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> optionalCustomer = customerRepository2.findById(customerId);

		if(!optionalCustomer.isPresent()) return;

		Customer customer = optionalCustomer.get();
		customerRepository2.delete(customer);


	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		//Avoid using SQL query
		Driver driver=null;
		List<Driver> drivers=driverRepository2.findAll();
		for(Driver d:drivers)
		{
			if(d.getCab().getisAvailable()==true)
			{
				if(driver==null || driver.getDriverId()>d.getDriverId())
					driver=d;
			}
		}
		if(driver==null)
			throw new Exception("No cab available!");
		Customer customer = customerRepository2.findById(customerId).get();

		TripBooking tripBooking = new TripBooking();
		tripBooking.setDistanceInKm(distanceInKm);
		tripBooking.setFromLocation(fromLocation);
		tripBooking.setToLocation(toLocation);
		tripBooking.setCustomer(customer);
		tripBooking.setDriver(driver);
		driver.getCab().setAvailable(false);
		tripBooking.setStatus(TripStatus.CONFIRMED);

		customer.getTripBookingList().add(tripBooking);
		driver.getTripBookingList().add(tripBooking);

		driverRepository2.save(driver);
		customerRepository2.save(customer);
		return tripBooking;
	}
	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

		Optional<TripBooking> optionalTripBooking = tripBookingRepository2.findById(tripId);

		if(!optionalTripBooking.isPresent()) return;

		TripBooking tripBooking = optionalTripBooking.get();
		tripBooking.setBill(0);
		tripBooking.setStatus(TripStatus.CANCELED);

		tripBooking.getDriver().getCab().setAvailable(true);
		tripBookingRepository2.save(tripBooking);

	}

	@Override
	public void completeTrip(Integer tripId){
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.COMPLETED);
		int bill = tripBooking.getDriver().getCab().getPerKmRate()*tripBooking.getDistanceInKm();
		tripBooking.getDriver().getCab().setAvailable(true);
		tripBooking.setBill(bill);

		tripBookingRepository2.save(tripBooking);
	}
}
