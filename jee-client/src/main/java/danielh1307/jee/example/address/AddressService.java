package danielh1307.jee.example.address;

import javax.ejb.Remote;

/**
 * 
 * Interface for entity {@link Address}.
 *
 */
@Remote
public interface AddressService {

	/**
	 * Returns a specific address.
	 * 
	 * @param id
	 *            the id of the address
	 * @return the address with the given id. If the address does not exist,
	 *         null is returned.
	 */
	public Address getAddress(int id);

}
