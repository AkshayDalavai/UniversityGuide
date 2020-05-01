package com.univeristyguide.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univeristyguide.login.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByName(final String name);
}
