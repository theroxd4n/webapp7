package com.group7.voluntaweb.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.group7.voluntaweb.Models.User;
import com.group7.voluntaweb.Models.Volunteering;
import com.group7.voluntaweb.beans.VolAndCat;

public interface VolunteeringRepository extends JpaRepository<Volunteering, Long> {
	@Query("SELECT user FROM User user JOIN user.registrations r JOIN r.volunteering volunteering WHERE volunteering.id = :volunteeringId AND user.id = :userId")
	User findUserVolunteering(@Param("volunteeringId") long volunteeringId, @Param("userId") long userId);

	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE c.id = :c_id")
	Iterable<Volunteering> findByCategory(@Param("c_id") Long cid);

	// @Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id,
	// v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN
	// Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id")
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id")
	Iterable<VolAndCat> findAllVols();

	Volunteering findById(long id);

	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id WHERE v.name LIKE %:search%")
	Iterable<Volunteering> findByQuery(@Param("search") String search);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM users_volunteerings WHERE user_id = :user_id AND volunteering_id = :volunteering_id", nativeQuery = true)
	void deleteJoin(@Param("user_id") long user_id, @Param("volunteering_id") long volunteering_id);

	
	@Query("SELECT new com.group7.voluntaweb.beans.VolAndCat(v.id, v.name,v.image, v.city, c.name, o.name) FROM Volunteering v INNER JOIN Category c ON v.category = c.id INNER JOIN ONG o ON v.ong = o.id INNER JOIN UsersVolunteerings uv ON v.id = uv.volunteering WHERE uv.user = :user")
	Iterable<Volunteering> findMyVolunteerings(@Param("user") User user);
}