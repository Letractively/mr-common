package mr.common.security.userentity.dao;

import java.util.List;

import mr.common.dao.AbstractAuditableDao;
import mr.common.model.Pageable;
import mr.common.security.model.User;
import mr.common.security.userentity.model.Authority;
import mr.common.security.userentity.model.UserEntity;


/**
 * Interfaz DAO de {@link mr.common.security.userentity.model.UserEntity UserEntity}.
 * @author marruiz
 */
public interface UserEntityDao extends AbstractAuditableDao<UserEntity> {

	/**
	 * Obtiene el usuario por el nombre.
	 * @param username String
	 * @return user
	 */
	UserEntity getByUsername(String username);

	/**
	 * Obtiene el usuario por su dirección de email.
	 * @param emailAddress String
	 * @return user
	 */
	UserEntity getByEmailAddress(String emailAddress);

	/**
	 * Roles del usuario.
	 * @param username String
	 * @return {@link mr.common.security.model.Authority} roles
	 */
	List<Authority> getAuthorityList(String username);

	/**
	 * Roles del usuario.
	 * @param userId Long
	 * @return {@link mr.common.security.model.Authority} roles
	 */
	List<Authority> getAuthorityList(Long userId);

	/**
	 * Búsqueda de usuario según criterios pasados.
	 * @return lista de usuarios
	 */
	List<UserEntity> find(User user, Boolean activeFilter, Pageable page);

	/**
	 * Obtiene la cantidad de usuarios según criterios pasados.
	 * @return int
	 */
	int findCount(User user, Boolean activeFilter);
}
