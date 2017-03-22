package danielh1307.jee.example.address;

import javax.ejb.Remote;

@Remote
public interface AddressService {

	public Address getAddress(int id);
	
}
