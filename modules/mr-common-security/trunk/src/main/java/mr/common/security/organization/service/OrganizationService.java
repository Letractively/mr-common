package mr.common.security.organization.service;

import java.io.Serializable;
import java.util.List;

import mr.common.model.ConfigurableData;
import mr.common.security.organization.exception.DuplicatedOrganizationException;
import mr.common.security.organization.exception.InvalidOrganizationNameException;
import mr.common.security.organization.exception.OrganizationNotExistException;
import mr.common.security.organization.model.Organization;


/**
 * Servicio para el manejo de organizaciones de
 * la aplicación.
 *
 * @see mr.common.security.organization.model.Organization
 *
 * @author Mariano Ruiz
 */
public interface OrganizationService {


	/**
	 * @return listado de todas las organizaciones
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	List<Organization> getList();

	/**
	 * Busca organizaciones según los parámetros pasados
	 * y en forma pagina.
	 * @param nameOrDescription - nombre o descripción
	 * de la organización
	 * @param activeFilter - si es distinto de <code>null</code>,
	 * su valor indica si se debe filtrar organizaciones
	 * activados/desactivados
	 * @param page - página de datos, <code>null</code>
	 * si se deben traer todos los datos y sin ordenar
	 * @return listado de organizaciones
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	List<Organization> find(String nameOrDescription, Boolean activeFilter, ConfigurableData page);

	/**
	 * Obtiene la cantidad de organizaciones por determinados parámetros.
	 * @param nameOrDescription - nombre o descripción
	 * de la organización
	 * @param activeFilter - si es distinto de <code>null</code>,
	 * su valor indica si se debe filtrar usuarios
	 * activados/desactivados
	 * @return listado de organizaciones
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	int findCount(String nameOrDescription, Boolean activeFilter);

	/**
	 * @param id Serializable - identificador único de la organización
	 * @return la organización
	 * @throws OrganizationNotExistException si la organización no existe
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	Organization getById(Serializable id);

	/**
	 * Retorna la organización por su nombre.
	 * @param name String
	 * @return la organización
	 * @throws OrganizationNotExistException si la organización no existe
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	Organization getByName(String name);

	/**
	 * Crea la nueva organización con la información pasada.
	 * @param org: datos de la nueva organización
	 * @return la organización actualizada
	 * @throws InvalidOrganizationNameException Si el nombre
	 * de organización no es válido
	 * @throws DuplicatedOrganizationException Si una organización
	 * ya existe con el mismo nombre
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	Organization newOrganization(Organization org);

	/**
	 * Actualiza la información de la organización.
	 * @param id: identificador de la organización
	 * @param organization: datos nuevos
	 * @return la organización actualizada
	 * @throws OrganizationNotExistException si la organización
	 * no existe
	 * @throws InvalidOrganizationNameException Si el nombre
	 * de organización no es válido
	 * @throws DuplicatedOrganizationException Si una organización
	 * ya existe con el mismo nombre
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	Organization updateOrganization(Serializable id, Organization organization);

	/**
	 * Actualiza la información de la organización.
	 * @param name: nombre de la organización
	 * @param organization: datos nuevos
	 * @return la organización actualizada
	 * @throws OrganizationNotExistException si la organización
	 * no existe
	 * @throws InvalidOrganizationNameException Si el nombre
	 * de organización no es válido
	 * @throws DuplicatedOrganizationException Si una organización
	 * ya existe con el mismo nombre
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	Organization updateOrganization(String name, Organization organization);

	/**
	 * Retorna un nuevo objeto organización de la implementación
	 * usada. Esta instancia no representa una organización
	 * en el sistema, solo retorna un objeto 'en blanco',
	 * que puede ser usado para crear una nueva,
	 * o cargarle parámetros de búsqueda, etc.
	 * @return instancia de la implementación usada
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	Organization getOrganizationInstance();

	/**
	 * Valida si el nombre de organización pasado es válido.
	 * @param name String
	 * @return <code>true</code> si el nombre es válido
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	boolean isValidOrganizationName(String name);


	/**
	 * Borra la organización por su nombre.
	 * @throws OrganizationNotExistException si la organización
	 * no existe
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	void deleteByName(String name);

	/**
	 * Borra la organización por su id.
	 * @throws OrganizationNotExistException si la organización
	 * no existe
	 * @throws UnsupportedOperationException Si la operación
	 * no es soportada por la implementación
	 */
	void deleteById(Serializable id);
}