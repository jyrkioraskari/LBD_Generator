package de.rwth.aachen.ip.lbd;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.rwth.aachen.ip.lbd.objectmapping.model.Building;
import de.rwth.aachen.ip.lbd.objectmapping.model.Site;
import de.rwth.aachen.ip.lbd.objectmapping.model.Storey;
import de.rwth.aachen.ip.lbd.objectmapping.util.LBDModule;
import net.enilink.komma.core.IEntityManager;
import net.enilink.komma.core.IEntityManagerFactory;
import net.enilink.komma.core.KommaModule;
import net.enilink.komma.core.URI;
import net.enilink.komma.core.URIs;

public class CreateBOT {
	
	public CreateBOT() {

		// Set the object mapping
		SailRepository dataRepository = new SailRepository(new MemoryStore());
		dataRepository.init();
		IEntityManager manager = createEntityManager(new LBDModule(
				dataRepository, new KommaModule() {
					{
						addConcept(Site.class);
						addConcept(Building.class);
						addConcept(Storey.class);
					}
				}));

		URI INST_URI = URIs.createURI("http://rwth-aachen.de/ip/lbd/");
		
		// Create the model
		Site site = manager.createNamed(INST_URI.appendFragment("site1"),
				Site.class);

		Building building = manager.createNamed(INST_URI.appendFragment("building1"),
				Building.class);

		site.buildings().add(building);
		
		Storey storey1 = manager.createNamed(INST_URI.appendFragment("storey1"),
				Storey.class);
		
		building.storeys().add(storey1);
		
		
        // Print TURTLE		
		try (RepositoryConnection conn = dataRepository.getConnection()) {
			RepositoryResult<Statement> statements = conn.getStatements(null, null, null);
			  Model model = QueryResults.asModel(statements);
			  Rio.write(model, System.out, RDFFormat.TURTLE);
			}
		
	}

	private  IEntityManager createEntityManager(LBDModule module) {
		Injector injector = Guice.createInjector(module);
		IEntityManagerFactory factory = injector
				.getInstance(IEntityManagerFactory.class);
		IEntityManager manager = factory.get();
		return manager;
	}
	
	public static void main(String[] args) {

		new CreateBOT();
	}

	

	
	
	

}
