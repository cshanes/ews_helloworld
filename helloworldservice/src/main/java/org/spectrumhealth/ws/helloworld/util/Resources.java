package org.spectrumhealth.ws.helloworld.util;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.sql.DataSource;

import org.spectrumhealth.ws.helloworld.annotation.ContractDataSource;
import org.spectrumhealth.ws.helloworld.model.Contract;
import com.priorityhealth.pagination.controller.PaginationController;
import com.priorityhealth.pagination.dao.CachePaginationDao;
import com.priorityhealth.pagination.factory.CacheFactory;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources
{
	private static final String PAGINATION_CONFIG_FILE_NAME = "cache-config/helloworld-cache.xml";
	private static final String PAGINATION_CACHE_NAME = "helloworld-pagination";
	
	@Resource(mappedName = "java:jboss/datasources/helloworldserviceDS")
	private DataSource ds;

	@Produces
	@ContractDataSource
	public DataSource getDataSource()
	{
		return ds;
	}
	
	@Produces
	public PaginationController<Contract> getContractPaginationController()
	{
		PaginationController<Contract> paginationController = new PaginationController<Contract>();
		CachePaginationDao<Contract> paginationDao = new CachePaginationDao<Contract>();
		CacheFactory cacheFactory = new CacheFactory(PAGINATION_CONFIG_FILE_NAME, PAGINATION_CACHE_NAME);
		
		paginationDao.setCacheFactory(cacheFactory);
		paginationController.setPaginationDao(paginationDao);
		
		return paginationController;
	}
	

	@Produces
	public Logger produceLog( InjectionPoint injectionPoint )
	{
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

}
