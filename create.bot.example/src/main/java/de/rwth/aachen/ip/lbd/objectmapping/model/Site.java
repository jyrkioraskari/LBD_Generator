package de.rwth.aachen.ip.lbd.objectmapping.model;

import java.util.Set;

import net.enilink.composition.annotations.Iri;

@Iri(BOT_ns.NS + "Site")
public interface Site extends Zone {

	@Iri(BOT_ns.NS + "hasBuilding")
	Set<Building> buildings();

	Zone buildings(Set<Building> buildings);

}
