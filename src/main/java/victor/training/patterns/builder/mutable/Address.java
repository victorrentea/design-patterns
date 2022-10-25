package victor.training.patterns.builder.mutable;

// vanilla Java
class Address {
    private String streetName;
    private String city;
    private String country;

    public String getStreetName() {
        return streetName;
    }

    public Address setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
               "streetName='" + streetName + '\'' +
               ", city='" + city + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}
