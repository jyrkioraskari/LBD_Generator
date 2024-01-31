package de.rwth.aachen.ip.lbd.objectmapping.model;

import java.util.Set;

import net.enilink.composition.annotations.Iri;

@Iri(BOT_ns.NS + "Building")
public interface Building extends Zone {

	@Iri(BOT_ns.NS + "name")
	String getName();

	void setName(String name);

	@Iri(BOT_ns.NS + "hasStorey")
	Set<Storey> storeys();

	Zone storeys(Set<Storey> storeys);
}
