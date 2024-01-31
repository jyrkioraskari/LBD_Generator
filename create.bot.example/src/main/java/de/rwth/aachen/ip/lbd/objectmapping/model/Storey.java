package de.rwth.aachen.ip.lbd.objectmapping.model;

import net.enilink.composition.annotations.Iri;

@Iri(BOT_ns.NS + "Storey")
public interface Storey extends Zone {

	@Iri(BOT_ns.NS + "name")
	String getName();

	void setName(String name);

	
}
