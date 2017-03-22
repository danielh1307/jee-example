package danielh1307.jee.example.address.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.address.AddressService;
import danielh1307.jee.example.address.access.AddressAccess;
import danielh1307.jee.example.util.LogMethodInterceptor;
import danielh1307.jee.example.util.MDCInterceptor;

@Stateless(mappedName = "AddressService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors({MDCInterceptor.class, LogMethodInterceptor.class})
public class AddressServiceImpl implements AddressService {
	
	@EJB
	private AddressAccess access;

	@Override
	public Address getAddress(int id) {
		return access.get(id);
	}

}
