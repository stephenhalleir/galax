package microservices.backend.eir_cdr_repository_backend.enums;

/**
 * Enum to specify whether the CDR processor should process all rows in a
 * template file, or a random subset
 * 
 * @author stephenhall
 *
 */
public enum ProcessType {
	FULL,
	RANDOM
}
