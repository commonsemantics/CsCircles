/*
 * Copyright 2013 Common Semantics (commonsemantics.org)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.commonsemantics.grails.circles.model

import org.apache.commons.lang.builder.HashCodeBuilder
import org.commonsemantics.grails.groups.model.Group
import org.commonsemantics.grails.users.model.User

/**
* @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
*/
class UserCircle implements Serializable {

	Date dateCreated, lastUpdated // Grails automatic timestamping
	
	User user
	Circle circle
	
	static hasMany = [users: User, groups: Group]
		
	boolean equals(other) {
		if (!(other instanceof UserCircle)) {
			return false
		}

		other.user?.id == user?.id &&
			other.circle?.id == circle?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (circle) builder.append(circle.id)
		builder.toHashCode()
	}

	static UserCircle get(long userId, long circleId) {
		find 'from UserCircle where user.id=:userId and group.id=:circleId',
			[userId: userId, groupId: circleId]
	}
	
	static def getByUserId(long userId) {
		findAll 'from UserCircle where user.id=:userId',
			[userId: userId]
	}

	static UserCircle create(User user, Circle circle, boolean flush = false) {
		new UserCircle(user: user, circle: circle).save(flush: flush, insert: true)
	}

	static boolean remove(User user, Circle circle, boolean flush = false) {
		UserCircle instance = UserCircle.findByUserAndCircle(user, circle)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserCircle WHERE user=:user', [user: user]
	}

	static void removeAll(Circle circle) {
		executeUpdate 'DELETE FROM UserCircle WHERE circle=:circle', [circle: circle]
	}

	static mapping = {
		id composite: ['circle', 'user']
		version false
	}
}
