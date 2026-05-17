package se.kth.iv1350.model;

/**
 * Data about a customer.
 */
public final class CustomerDTO {
    private final String name;
    private final String id;
    private final String email;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;

    /**
     * Creates a new instance.
     *
     * @param id               The customer's ID.
     * @param name             The customer's name.
     * @param email            The customer's email.
     * @param bikeBrand        The brand of the bike.
     * @param bikeModel        The model of the bike.
     * @param bikeSerialNumber The serial number of the bike.
     */
    public CustomerDTO(String id, String name, String email, String bikeBrand, 
                       String bikeModel, String bikeSerialNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
    }

    /**
     * Returns the customer's ID.
     * @return The customer's ID.
     */
    public String getId() { return id; }

    /**
     * Returns the customer's name.
     * @return The customer's name.
     */
    public String getName() { return name; }

    /**
     * Returns the customer's email.
     * @return The customer's email.
     */
    public String getEmail() { return email; }

    /**
     * Returns the bike brand.
     * @return The bike brand.
     */
    public String getBikeBrand() { return bikeBrand; }

    /**
     * Returns the bike model.
     * @return The bike model.
     */
    public String getBikeModel() { return bikeModel; }

    /**
     * Returns the bike serial number.
     * @return The bike serial number.
     */
    public String getBikeSerialNumber() { return bikeSerialNumber; }

    @Override
    public String toString() {
        return "Customer: " + name + " (" + id + "), Email: " + email + 
               ", Bike: " + bikeBrand + " " + bikeModel + " [" + bikeSerialNumber + "]";
    }
}
