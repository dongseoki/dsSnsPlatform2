package com.dssns.common.security;

import com.dssns.common.entity.Role;
import java.util.Collection;

public interface AbstractUserAuthInfo {
	long getId();

	Collection<Role> getAuthorities();
}
