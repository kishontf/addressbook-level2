package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

	private Block block;
	private Street street;
	private Unit unit;
	private PostalCode postalCode;
	
	private static final int BLOCK_INDEX = 0;
	private static final int STREET_INDEX = 1;
	private static final int UNIT_INDEX = 2;
	private static final int POSTALCODE_INDEX = 3;
	
    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can only be entered in the format: "
    		+ "a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] splitup = trimmedAddress.split(",");
        createClasses(splitup);
        this.value = trimmedAddress;
    }
    
    private String getAddress() {
    	return this.block.getName() + "," + this.street.getName() + ","
    			+ this.unit.getName() + "," + this.postalCode.getPostalCode();
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }
    
    private void createClasses(String[] split) {
    	block = new Block(split[BLOCK_INDEX]);
    	street = new Street(split[STREET_INDEX]);
    	unit = new Unit(split[UNIT_INDEX]);
    	postalCode = new PostalCode(split[POSTALCODE_INDEX]);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
